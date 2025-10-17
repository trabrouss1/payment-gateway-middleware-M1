package ci.trabrouss.orchestratorservice.controller;

import ci.trabrouss.orchestratorservice.controller.api.OrchestratorApi;
import ci.trabrouss.orchestratorservice.service.OrchestratorService;
import ci.trabrouss.orchestratorservice.service.OrchestratorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrchestratorController implements OrchestratorApi {

    private final OrchestratorService orchestratorService;

    public ResponseEntity<String> orchestrateTransaction(Map<String, Object> notificationDetails) {
        return null;
    }
}
