package yourssu.assignment.error;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"timeStamp", "code", "message", "data"})
public class ErrorResponse<T> {

    private LocalDateTime localDateTime;
    private final String code;
    private final String message;
    private final T data;




    public static <T> ErrorResponse<T> onFailure(String code, String message, T data){
        return new ErrorResponse<>(LocalDateTime.now(), code, message, data);
    }
}
