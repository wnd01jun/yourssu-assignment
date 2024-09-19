package yourssu.assignment.domain.article.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yourssu.assignment.domain.article.converter.ArticleConverter;
import yourssu.assignment.domain.article.dto.ArticleRequestDTO;
import yourssu.assignment.domain.article.dto.ArticleResponseDTO;
import yourssu.assignment.domain.article.entity.Article;
import yourssu.assignment.domain.article.repository.ArticleRepository;
import yourssu.assignment.domain.user.entity.User;
import yourssu.assignment.domain.user.service.UserService;
import yourssu.assignment.error.code.status.ErrorStatus;
import yourssu.assignment.error.exception.GeneralException;


@Service
@Transactional
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;
    private final UserService userService;

    @Override
    public ArticleResponseDTO.ArticlePostResponse postArticle(ArticleRequestDTO.ArticlePostRequest dto) {
        User user = userService.login(dto.getEmail(), dto.getPassword());
        Article article = ArticleConverter.postToArticle(dto, user);
        articleRepository.save(article);
        return ArticleConverter.articleToPost(article);

    }

    @Override
    public ArticleResponseDTO.ArticleModifyResponse modifyArticle(ArticleRequestDTO.ArticleModifyRequest dto, Long articleId) {
        Article article = findArticle(articleId);
        loginAndUpdateArticle(dto, article);

        return ArticleConverter.articleToModify(article);
    }

    private void loginAndUpdateArticle(ArticleRequestDTO.ArticleModifyRequest dto, Article article) {
        if(checkMasterOfPost(article, dto.getEmail(), dto.getPassword())) {
            article.updateArticle(dto);
        } else {
            throw new GeneralException(ErrorStatus._UNAUTHORIZED);
        }
    }

    @Override
    public void withdrawArticle(ArticleRequestDTO.ArticleWithdrawRequest dto, Long articleId) {
        Article article = findArticle(articleId);
        loginAndWithdrawArticle(dto, article);

    }

    private void loginAndWithdrawArticle(ArticleRequestDTO.ArticleWithdrawRequest dto, Article article) {
        if(checkMasterOfPost(article, dto.getEmail(), dto.getPassword())) {
            articleRepository.delete(article);
        } else {
            throw new GeneralException(ErrorStatus._UNAUTHORIZED);
        }
    }

    public Article findArticle(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(() -> new GeneralException(ErrorStatus._ARTICLE_NOT_FOUND));
    }
    private boolean checkMasterOfPost(Article article, String email, String password) {
        User user = userService.login(email, password);
        return article.getUser().getId().equals(user.getId());
    }
}
