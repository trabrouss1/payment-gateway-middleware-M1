package ci.trabrouss.authservice.contract.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * Classe générique pour structurer les réponses de l'API.
 *
 * @param <T> Le type de l'objet de données retourné.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T> implements Serializable {

    private Integer status; // Code HTTP (ex : 200, 400, 500)
    private Boolean success; // Indicateur de succès
    private String  message;  // Message général
    private T       data;          // Données à retourner en cas de succès

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private ErrorResponse error; // Objet d'erreur structuré


    /**
     * Classe interne pour structurer les détails des erreurs.
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ErrorResponse {
        private ErrorCode code;    // Code interne de l'erreur (ex : VALIDATION_ERROR)
        private String    message;    // Message général de l'erreur
        private Object    details;    // Détails de l'erreur (peut être une liste de FieldErrorDetail ou une chaîne de caractères)
    }

    /**
     * Classe pour structurer les détails d'un champ en erreur.
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FieldErrorDetail {
        private String field;    // Le nom du champ en erreur
        private String error;    // Le code d'erreur associé au champ (ex : FORMAT_INVALID, TOO_SHORT)
        private String message;  // Le message d'erreur pour ce champ
    }

    public static <T> APIResponse<T> buildResponse(T data, HttpStatus status, String message) {
        return APIResponse.<T>builder()
                .status(status.value())
                .success(status.is2xxSuccessful())
                .message(message)
                .data(data)
                .build();
    }

    public static <T> APIResponse<T> buildVoidResponse(HttpStatus status, String message) {
        return APIResponse.<T>builder()
                .status(status.value())
                .success(status.is2xxSuccessful())
                .message(message)
                .build();
    }

    /**
     * Méthode utilitaire pour construire une réponse d'erreur.
     */
    public static <T> APIResponse<T> buildErrorResponse(HttpStatus status, ErrorCode code, String message, Object details) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(code)
                .message(message)
                .details(details)
                .build();

        return APIResponse.<T>builder()
                .status(status.value())
                .success(false)
                .error(errorResponse)
                .build();
    }
}
