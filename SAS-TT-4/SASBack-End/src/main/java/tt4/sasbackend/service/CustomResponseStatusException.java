package tt4.sasbackend.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import tt4.sasbackend.DTO.errorReportDetailsDTO;
import java.util.List;

public class CustomResponseStatusException extends ResponseStatusException {
    private List<errorReportDetailsDTO> errorDetails;

    public CustomResponseStatusException(HttpStatus status, String reason, List<errorReportDetailsDTO> errorDetails) {
        super(status, reason);
        this.errorDetails = errorDetails;
    }

    public List<errorReportDetailsDTO> getErrorDetails() {
        return errorDetails;
    }
}
