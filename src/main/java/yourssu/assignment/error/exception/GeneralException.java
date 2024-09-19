package yourssu.assignment.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import yourssu.assignment.error.code.BaseErrorCode;
import yourssu.assignment.error.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException{

    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason(){
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}
