package yourssu.assignment.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import yourssu.assignment.domain.user.validation.ExistEmail;
import yourssu.assignment.domain.user.validation.NotExistEmail;

public class UserRequestDTO {

    @Getter
    @Builder
    public static class UserRegisterRequest {
        @NotBlank
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$")
        @NotExistEmail
        private String email;
        @NotBlank
        private String password;
        @NotBlank
        private String username;
    }

    @Getter
    @Builder
    public static class UserWithdrawRequest {
        @ExistEmail
        private String email;
        @NotBlank
        private String password;
    }
}
