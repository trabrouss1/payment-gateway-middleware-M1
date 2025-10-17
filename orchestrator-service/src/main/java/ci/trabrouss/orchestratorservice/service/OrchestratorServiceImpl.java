package ci.trabrouss.orchestratorservice.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class OrchestratorServiceImpl implements OrchestratorService {

    private KafkaTemplate<String, String> kafkaTemplate;

    private final RestTemplate restTemplate = new RestTemplate();

    public void orchestrateTransaction(Map<String, Object> notificationDetails) {
        // Logique d'orchestration des notifications
        System.out.println("Orchestrating notification: " + notificationDetails);

        // Simuler l'appel au service de paiement
        // Dans un vrai scénario, on utiliserait un service discovery (Eureka) et un client Feign
        // Pour l'instant, nous allons simuler un appel direct ou via Kafka
        // restTemplate.postForEntity("http://payment-service/process", notificationDetails, String.class);

        // Envoyer un message à Kafka pour le traitement asynchrone par les microservices
        kafkaTemplate.send("notification-initiation", notificationDetails.toString());

        System.out.println("Notification details sent to Kafka topic 'notification-initiation'");

        // Ici, on pourrait ajouter une logique pour attendre les réponses des autres services
        // ou gérer les retries et l'idempotence.
    }
}
