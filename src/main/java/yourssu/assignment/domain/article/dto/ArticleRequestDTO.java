package yourssu.assignment.domain.article.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import yourssu.assignment.domain.user.validation.ExistEmail;

public class ArticleRequestDTO {

    @Getter
    @Builder
    public static class ArticlePostRequest {
        @ExistEmail
        private String email;
        private String password;
        @NotBlank
        private String title;
        @NotBlank
        private String content;
    }

    @Getter
    @Builder
    public static class ArticleModifyRequest {
        @ExistEmail
        private String email;
        private String password;
        @NotBlank
        private String title;
        @NotBlank
        private String content;
    }

    @Getter
    @Builder
    public static class ArticleWithdrawRequest {
        @ExistEmail
        private String email;
        private String password;
    }
}
