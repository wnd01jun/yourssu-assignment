package yourssu.assignment.domain.article.service;

import yourssu.assignment.domain.article.dto.ArticleRequestDTO;
import yourssu.assignment.domain.article.dto.ArticleResponseDTO;

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
}
