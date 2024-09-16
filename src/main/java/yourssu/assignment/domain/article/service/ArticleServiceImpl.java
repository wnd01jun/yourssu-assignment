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

import java.util.NoSuchElementException;

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
        updateArticle(dto, article);

        return ArticleConverter.articleToModify(article);
    }

    private void updateArticle(ArticleRequestDTO.ArticleModifyRequest dto, Article article) {
        if(checkMasterOfPost(article, dto.getEmail(), dto.getPassword())) {
            article.updateArticle(dto);
        } else {
            throw new RuntimeException("로그인 불가");
        }
    }

    @Override
    public void withdrawArticle(ArticleRequestDTO.ArticleWithdrawRequest dto, Long articleId) {
        Article article = findArticle(articleId);
        withdrawArticle(dto, article);

    }

    private void withdrawArticle(ArticleRequestDTO.ArticleWithdrawRequest dto, Article article) {
        if(checkMasterOfPost(article, dto.getEmail(), dto.getPassword())) {
            articleRepository.delete(article);
        } else {
            throw new RuntimeException("로그인 불가");
        }
    }

    private Article findArticle(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(() -> new NoSuchElementException());
    }
    private boolean checkMasterOfPost(Article article, String email, String password) {
        User user = userService.login(email, password);
        if(article.getUser().getId() == user.getId()) {
            return true;
        }
        return false;
    }
}
