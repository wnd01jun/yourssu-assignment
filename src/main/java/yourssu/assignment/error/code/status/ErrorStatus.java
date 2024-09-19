package yourssu.assignment.error.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import yourssu.assignment.error.code.BaseErrorCode;
import yourssu.assignment.error.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {


    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버에러"),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청"),
    _USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404", "해당 사용자를 찾을 수 없습니다."),
    _COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMENT404", "해당 댓글을 찾을 수 없습니다.") ,
    _ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND,"ARTICLE404", "해당 게시글을 찾을 수 없습니다." ),
    _PASSWORD_BAD_REQUEST(HttpStatus.UNAUTHORIZED, "PASSWORD401", "비밀번호가 일치하지 않습니다."),
    _EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "EMAIL404", "해당 이메일을 찾을 수 없습니다."),
    _EMAIL_CONFLICT(HttpStatus.CONFLICT, "EMAIL409", "해당 이메일은 이미 가입한 이메일입니다."),
    PAGE_LOWER_ZERO(HttpStatus.BAD_REQUEST, "PAGE4001", "요청된 페이지가 0보다 작습니다.");


    private HttpStatus httpStatus;
    private String code;
    private String message;


    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
