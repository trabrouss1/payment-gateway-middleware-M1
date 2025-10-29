package ci.trabrouss.authservice.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

public class CustomAPIException extends RuntimeException {

    private final HttpStatus status;
    private final String message;

    public CustomAPIException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public ResponseEntity<APIError<Object>> createErrorResponse() {
        APIError<Object> apiError = APIError.builder()
                .title(this.status.getReasonPhrase())
                .status(this.status.value())
                .message(Collections.singletonList(this.message))
                .build();
        return new ResponseEntity<>(apiError, new HttpHeaders(), this.status);
    }

    public HttpStatus getStatus() {
        return status;
    }
}
