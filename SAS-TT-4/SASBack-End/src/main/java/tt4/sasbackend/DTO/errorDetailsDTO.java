package tt4.sasbackend.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.net.URI;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class errorDetailsDTO {
    private int status;
    private String title;
    private URI instance;
    private List<errorReportDetailsDTO> detail;

    public errorDetailsDTO(int status, String title, URI instance, List<errorReportDetailsDTO> detail) {
        this.status = status;
        this.title = title;
        this.instance = instance;
        this.detail = detail;
    }
}
