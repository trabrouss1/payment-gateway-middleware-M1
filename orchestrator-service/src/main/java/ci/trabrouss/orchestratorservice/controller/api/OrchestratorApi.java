package ci.trabrouss.orchestratorservice.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/orchestrators")
//@Tag(name = "Gestion des orchestrations", description = "API de gestion des orchestrations")
public interface OrchestratorApi {

    ResponseEntity<String> orchestrateTransaction(@RequestBody Map<String, Object> notificationDetails);
}
