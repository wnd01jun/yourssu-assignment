package yourssu.assignment.domain.article.converter;

import yourssu.assignment.domain.article.dto.ArticleRequestDTO;
import yourssu.assignment.domain.article.dto.ArticleResponseDTO;
import yourssu.assignment.domain.article.entity.Article;
import yourssu.assignment.domain.user.entity.User;

public class ArticleConverter {

    public static Article postToArticle(ArticleRequestDTO.ArticlePostRequest dto, User user) {
        return Article.builder()
                .user(user)
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    public static ArticleResponseDTO.ArticlePostResponse articleToPost(Article article) {
        return ArticleResponseDTO.ArticlePostResponse.builder()
                .articleId(article.getId())
                .email(article.getUser().getEmail())
                .title(article.getTitle())
                .content(article.getContent())
                .build();
    }

    public static ArticleResponseDTO.ArticleModifyResponse articleToModify(Article article) {
        return ArticleResponseDTO.ArticleModifyResponse.builder()
                .articleId(article.getId())
                .email(article.getUser().getEmail())
                .title(article.getTitle())
                .content(article.getContent())
                .build();
    }
}
