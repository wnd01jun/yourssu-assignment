package yourssu.assignment.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import yourssu.assignment.domain.article.validation.ExistArticle;
import yourssu.assignment.domain.user.validation.ExistEmail;

public class CommentRequestDTO {

    @Getter
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
    public static class CommentModifyRequest {
        @ExistEmail
        private String email;

        private String password;

        @NotBlank
        private String content;
    }

    @Getter
    public static class CommentWithdrawRequest {
        @ExistEmail
        private String email;

        private String password;
    }
}
