package ci.trabrouss.paymentservice.controller;

import ci.trabrouss.paymentservice.controller.api.PaymentApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PaymentController implements PaymentApi {


    public ResponseEntity<String> processPayment(Map<String, Object> paymentDetails) {
        return null;
    }
}
