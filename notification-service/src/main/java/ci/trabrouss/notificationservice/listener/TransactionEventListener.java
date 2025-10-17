package ci.trabrouss.notificationservice.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionEventListener {

    @KafkaListener(topics = "transaction-initiation", groupId = "notification-group")
    public void listen(String message) {
        System.out.println("Received transaction initiation event: " + message);
        // Logique de notification (email, SMS, push notification)
        System.out.println("Sending notification for transaction: " + message);
    }
}
