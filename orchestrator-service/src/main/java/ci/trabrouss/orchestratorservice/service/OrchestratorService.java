package ci.trabrouss.orchestratorservice.service;

import java.util.Map;

public interface OrchestratorService {
    void orchestrateTransaction(Map<String, Object> notificationDetails);
}
