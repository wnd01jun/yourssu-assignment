package yourssu.assignment.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

public class UserResponseDTO {

    @Getter
    @Builder
    public static class UserRegisterResponse {
        private String email;
        private String username;
    }
}
