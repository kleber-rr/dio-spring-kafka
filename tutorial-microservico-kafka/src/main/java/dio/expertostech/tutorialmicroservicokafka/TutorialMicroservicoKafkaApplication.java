package dio.expertostech.tutorialmicroservicokafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class TutorialMicroservicoKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorialMicroservicoKafkaApplication.class, args);
	}

}
