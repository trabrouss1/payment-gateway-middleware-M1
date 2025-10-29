package ci.trabrouss.authservice.contract.request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
public class RequestBase {


    @Min(0)
    private int page = 0;

    @Min(1)
    private int size = 10;

    private List<PaginationSortItemDTO> sortItems;

    private Long id;
    private LocalDateTime createdAtFrom;
    private LocalDateTime createdAtTo;

    private LocalDateTime updatedAtFrom;
    private LocalDateTime updatedAtTo;
    private Long createdBy;
    private Long updatedBy;


}
