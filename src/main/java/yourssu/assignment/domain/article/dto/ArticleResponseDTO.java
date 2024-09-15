package yourssu.assignment.domain.article.dto;

public class ArticleResponseDTO {

    public static class ArticlePostResponse {
        private Long articleId;

        private String email;

        private String title;

        private String content;
    }

    public static class ArticleModifyResponse {
        private Long articleId;

        private String email;

        private String title;

        private String content;
    }
}
