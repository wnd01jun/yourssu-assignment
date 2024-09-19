package yourssu.assignment.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import yourssu.assignment.domain.article.validation.ExistArticle;
import yourssu.assignment.domain.user.validation.ExistEmail;

public class CommentRequestDTO {

    @Getter
    @Builder
    public static class CommentPostRequest {
        @ExistEmail
        private String email;

        private String password;

        @NotBlank
        private String content;

        @ExistArticle
        private Long articleId;
    }

    @Getter
    @Builder
    public static class CommentModifyRequest {
        @ExistEmail
        private String email;

        private String password;

        @NotBlank
        private String content;
    }

    @Getter
    @Builder
    public static class CommentWithdrawRequest {
        @ExistEmail
        private String email;

        private String password;
    }
}
