package tt4.sasbackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class errorReportDetailsDTO {
    private String field;
    private String errorMessage;
}
