package dio.expertostech.tutorialrestkafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistraEventoService {

    private final KafkaTemplate<Object,Object> templateKafka;

    public <T> void adcionarEvento(String topico, T dados) {
        templateKafka.send(topico, dados);
    }
}
