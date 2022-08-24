package dio.expertostech.tutorialmicroservicokafka.service;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dio.expertostech.tutorialmicroservicokafka.data.PedidoData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalvarPedidoService {

    private final KafkaTemplate<Object,Object> templateKafka;

    public <T> void adcionarEvento(String topico, T dados) {
        templateKafka.send(topico, dados);
    }

    @KafkaListener(topics = "salvarPedido", groupId = "MicroservicoSalvaPedido")
    private void executar(ConsumerRecord<String,String> record){
        log.info("Chave = {}", record.key());
        log.info("Cabecalho = {}", record.headers());
        log.info("Particao = {}", record.partition());

        String strDados = record.value();
        ObjectMapper mapper = new ObjectMapper();
        PedidoData pedido;
        try {
            pedido = mapper.readValue(strDados, PedidoData.class);
        } catch (JsonProcessingException e) {
            log.error("Falha o converter evento [dado={}]", strDados, e);
            return;
        }
        String uuid = String.valueOf(UUID.randomUUID());
        pedido.setUuid(uuid);
        log.info("Evento recebido = {}", pedido);

        if(gravarPedido(pedido)){
            retornarTopico(pedido);
        }
    }

    private void retornarTopico(PedidoData pedido) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String strMapper = mapper.writeValueAsString(pedido);
            adcionarEvento("retornoPedido", strMapper);
        } catch (JsonProcessingException e) {
            log.error("Falha o converter evento para retorno", pedido.getUuid(), e);
        }
    }

    private boolean gravarPedido(PedidoData pedido) {
        log.info("gravando pedido [PedidoDado={}]", pedido);
        return true;
    }


}
