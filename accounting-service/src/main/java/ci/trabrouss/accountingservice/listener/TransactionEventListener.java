package ci.trabrouss.accountingservice.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionEventListener {

    @KafkaListener(topics = "transaction-initiation", groupId = "accounting-group")
    public void listen(String message, String logId) {
        log.info("[{}][TransactionEventListener][listen] Received transaction initiation event for accounting: {}", logId, message);
        // Logique de comptabilité
        log.info("[{}][TransactionEventListener][listen] Processing accounting for transaction: {}", logId, message);
    }
}
