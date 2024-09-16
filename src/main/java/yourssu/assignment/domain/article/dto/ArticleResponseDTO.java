package yourssu.assignment.domain.article.dto;

import lombok.Builder;
import lombok.Getter;

public class ArticleResponseDTO {

    @Getter
    @Builder
    public static class ArticlePostResponse {
        private Long articleId;

        private String email;

        private String title;

        private String content;
    }

    @Getter
    @Builder
    public static class ArticleModifyResponse {
        private Long articleId;

        private String email;

        private String title;

        private String content;
    }
}
