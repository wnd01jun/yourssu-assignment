package yourssu.assignment.domain.article.service;

import yourssu.assignment.domain.article.dto.ArticleRequestDTO;
import yourssu.assignment.domain.article.dto.ArticleResponseDTO;
import yourssu.assignment.domain.article.entity.Article;

public interface ArticleService {

    ArticleResponseDTO.ArticlePostResponse postArticle(
            ArticleRequestDTO.ArticlePostRequest dto
    );

    ArticleResponseDTO.ArticleModifyResponse modifyArticle(
            ArticleRequestDTO.ArticleModifyRequest dto, Long articleId
    );

    void withdrawArticle(
            ArticleRequestDTO.ArticleWithdrawRequest dto, Long articleId
    );

    Article findArticle(Long articleId);
}
