package yourssu.assignment.domain.article.service;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import yourssu.assignment.domain.article.dto.ArticleRequestDTO;
import yourssu.assignment.domain.article.dto.ArticleResponseDTO;
import yourssu.assignment.domain.article.entity.Article;
import yourssu.assignment.domain.article.repository.ArticleRepository;
import yourssu.assignment.domain.user.dto.UserRequestDTO;
import yourssu.assignment.domain.user.dto.UserResponseDTO;
import yourssu.assignment.domain.user.entity.User;
import yourssu.assignment.domain.user.service.UserService;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ArticleServiceTest {

    @Autowired
    public ArticleService articleService;
    @Autowired
    public ArticleRepository articleRepository;
    @Autowired
    UserService userService;

    static String LOGIN_ID = "changjun157@naver.com";
    static String LOGIN_PASSWORD = "qwer1234";
    static String USERNAME = "테스트1";

    static String ARTICLE_TITLE = "yourssu";
    static String ARTICLE_CONTENT = "yourssu_assignment";

    static Long articleId;

    @BeforeEach
    void init() {
        UserRequestDTO.UserRegisterRequest dto1 = UserRequestDTO.UserRegisterRequest.builder()
                .username(USERNAME)
                .email(LOGIN_ID)
                .password(LOGIN_PASSWORD)
                .build();

        UserResponseDTO.UserRegisterResponse response = userService.registerUser(dto1);
        User user = userService.login(LOGIN_ID, LOGIN_PASSWORD);


        Article article = Article.builder()
                .title(ARTICLE_TITLE)
                .content(ARTICLE_CONTENT)
                .user(user)
                .build();

        articleRepository.save(article);
        articleId = article.getId();

    }


    @Test
    void postArticle() {
        ArticleRequestDTO.ArticlePostRequest dto = ArticleRequestDTO.ArticlePostRequest.builder()
                .title("Hello")
                .content("gogogo")
                .email(LOGIN_ID)
                .password(LOGIN_PASSWORD)
                .build();

        ArticleResponseDTO.ArticlePostResponse result = articleService.postArticle(dto);
        Article article = articleRepository.findById(result.getArticleId()).orElseThrow(() -> new NoSuchElementException());

        Assertions.assertThat(result.getTitle()).isEqualTo(article.getTitle());
        Assertions.assertThat(result.getContent()).isEqualTo(article.getContent());
    }

    @Test
    void modifyArticle() {
        Article article = articleRepository.findById(articleId).get();

        ArticleRequestDTO.ArticleModifyRequest dto = ArticleRequestDTO.ArticleModifyRequest.builder()
                .title("good")
                .content("yourssu")
                .email(LOGIN_ID)
                .password(LOGIN_PASSWORD)
                .build();
        articleService.modifyArticle(dto, article.getId());

        Assertions.assertThat(article.getTitle()).isEqualTo("good");
        Assertions.assertThat(article.getContent()).isEqualTo("yourssu");



    }

    @Test
    void withdrawArticle() {
        Article article = articleRepository.findById(articleId).get();

        ArticleRequestDTO.ArticleWithdrawRequest dto = ArticleRequestDTO.ArticleWithdrawRequest.builder()
                .email(LOGIN_ID)
                .password(LOGIN_PASSWORD)
                .build();
        articleService.withdrawArticle(dto, article.getId());

        Assertions.assertThat(articleRepository.findById(articleId).isEmpty()).isTrue();
    }

}