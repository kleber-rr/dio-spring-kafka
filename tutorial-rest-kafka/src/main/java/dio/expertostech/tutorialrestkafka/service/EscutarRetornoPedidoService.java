package dio.expertostech.tutorialrestkafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dio.expertostech.tutorialrestkafka.data.PedidoData;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EscutarRetornoPedidoService {

    @KafkaListener(topics = "retornoPedido", groupId = "MicroservicoSalvaPedido")
    private void executar(ConsumerRecord<String, String> consumer) {
        log.info("Chave = {}", consumer.key());
        log.info("Cabecalho = {}", consumer.headers());
        log.info("Particao = {}", consumer.partition());

        String strDados = consumer.value();
        ObjectMapper mapper = new ObjectMapper();
        PedidoData pedido;
        try {
            pedido = mapper.readValue(strDados, PedidoData.class);
        } catch (JsonProcessingException e) {
            log.error("Falha o converter evento [dado={}]", strDados, e);
            return;
        }
        log.info("Producer recebido = {}", pedido);
    }
}
