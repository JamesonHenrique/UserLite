package usermanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Data;



import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class ErrorDetails {

    private LocalDateTime timestamp;
    private String path;
    private String message;
    private String errorCode;
}
