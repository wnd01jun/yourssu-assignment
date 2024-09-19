package yourssu.assignment.error.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@AllArgsConstructor
public class ErrorReasonDTO {
    String message;
    String code;
    Boolean isSuccess;
    HttpStatus httpStatus;
}
