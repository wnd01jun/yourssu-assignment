package yourssu.assignment.domain.article.service;

import jakarta.persistence.EntityManager;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
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

import static org.assertj.core.api.Assertions.*;
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
    @Autowired
    EntityManager em;

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

        assertThat(result.getTitle()).isEqualTo(article.getTitle());
        assertThat(result.getContent()).isEqualTo(article.getContent());
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

        assertThat(article.getTitle()).isEqualTo("good");
        assertThat(article.getContent()).isEqualTo("yourssu");



    }

    @Test
    void withdrawArticle() {
        Article article = articleRepository.findById(articleId).get();

        ArticleRequestDTO.ArticleWithdrawRequest dto = ArticleRequestDTO.ArticleWithdrawRequest.builder()
                .email(LOGIN_ID)
                .password(LOGIN_PASSWORD)
                .build();
        articleService.withdrawArticle(dto, article.getId());

        assertThat(articleRepository.findById(articleId).isEmpty()).isTrue();
    }

    @Test
    @Rollback(false)
    @DisplayName(value = "유저가 삭제되면 해당 유저와 관련된 Article도 삭제되어야 한다.")
    void deleteUser() {
        User user = userService.login(LOGIN_ID, LOGIN_PASSWORD);

        Article article1 = Article.builder()
                .title("article1")
                .content("articleContent1")
                .user(user)
                .build();
        Article article2 = Article.builder()
                .title("article2")
                .content("articleContent2")
                .user(user)
                .build();
        Article article3 = Article.builder()
                .title("article3")
                .content("articleContent3")
                .user(user)
                .build();

        articleRepository.save(article1);
        articleRepository.save(article2);
        articleRepository.save(article3);

        UserRequestDTO.UserWithdrawRequest withdrawRequest = UserRequestDTO.UserWithdrawRequest.builder()
                .email(LOGIN_ID)
                .password(LOGIN_PASSWORD)
                .build();

        userService.withdrawUser(withdrawRequest);
        em.clear();

        assertThat(articleRepository.findById(article1.getId()).isEmpty()).isTrue();
        assertThat(articleRepository.findById(article2.getId()).isEmpty()).isTrue();
        assertThat(articleRepository.findById(article3.getId()).isEmpty()).isTrue();

    }



}