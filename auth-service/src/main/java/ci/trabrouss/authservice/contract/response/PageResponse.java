package ci.trabrouss.authservice.contract.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"status", "success", "message", "data"})
public class PageResponse<T> {
    private Integer             status;
    private Boolean             success;
    private String              message;
    private T                   data;
    private Map<String, Object> links;
    private Map<String, Object> page;
}
