package ci.trabrouss.authservice.Exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIError<T> {
    private Integer status;
    private String title;
    private final Boolean success = false;
    private T message;
    private String error;


}