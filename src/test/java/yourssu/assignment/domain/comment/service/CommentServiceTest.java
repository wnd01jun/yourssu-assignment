package yourssu.assignment.domain.comment.service;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import yourssu.assignment.domain.article.dto.ArticleRequestDTO;
import yourssu.assignment.domain.article.dto.ArticleResponseDTO;
import yourssu.assignment.domain.article.entity.Article;
import yourssu.assignment.domain.article.service.ArticleService;
import yourssu.assignment.domain.comment.dto.CommentRequestDTO;
import yourssu.assignment.domain.comment.dto.CommentResponseDTO;
import yourssu.assignment.domain.comment.entity.Comment;
import yourssu.assignment.domain.comment.repository.CommentRepository;
import yourssu.assignment.domain.user.dto.UserRequestDTO;
import yourssu.assignment.domain.user.dto.UserResponseDTO;
import yourssu.assignment.domain.user.entity.User;
import yourssu.assignment.domain.user.service.UserService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentService commentService;
    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;
    @Autowired
    EntityManager em;

    static String LOGIN_ID = "changjun157@naver.com";
    static String LOGIN_PASSWORD = "qwer1234";
    static String USERNAME = "테스트1";

    static String ARTICLE_TITLE = "yourssu";
    static String ARTICLE_CONTENT = "yourssu_assignment";

    static String COMMENT_CONTENT = "testest";

    static Long articleId;
    static Long commentId;


    @BeforeEach
    void init() {
        UserRequestDTO.UserRegisterRequest dto1 = UserRequestDTO.UserRegisterRequest.builder()
                .username(USERNAME)
                .email(LOGIN_ID)
                .password(LOGIN_PASSWORD)
                .build();

        UserResponseDTO.UserRegisterResponse response = userService.registerUser(dto1);
        User user = userService.login(LOGIN_ID, LOGIN_PASSWORD);


        ArticleRequestDTO.ArticlePostRequest articleDto = ArticleRequestDTO.ArticlePostRequest.builder()
                .title(ARTICLE_TITLE)
                .content(ARTICLE_CONTENT)
                .email(LOGIN_ID)
                .password(LOGIN_PASSWORD)
                .build();

        ArticleResponseDTO.ArticlePostResponse articleResponse = articleService.postArticle(articleDto);
        articleId = articleResponse.getArticleId();
        Article article = articleService.findArticle(articleId);

        Comment comment = Comment.builder()
                .user(user)
                .article(article)
                .content(COMMENT_CONTENT)
                .build();

        commentRepository.save(comment);
        commentId = comment.getId();
    }

    @Test
    void postComment() {
        CommentRequestDTO.CommentPostRequest dto = CommentRequestDTO.CommentPostRequest.builder()
                .articleId(articleId)
                .email(LOGIN_ID)
                .password(LOGIN_PASSWORD)
                .content(COMMENT_CONTENT)
                .build();
        CommentResponseDTO.CommentPostResponse commentResponse = commentService.postComment(dto);

        Comment comment = commentRepository.findById(commentResponse.getCommentId()).get();
        Article article = articleService.findArticle(articleId);
        User user = userService.login(LOGIN_ID, LOGIN_PASSWORD);

        assertThat(comment.getContent()).isEqualTo(COMMENT_CONTENT);
        assertThat(comment.getUser()).isEqualTo(user);
        assertThat(comment.getArticle()).isEqualTo(article);

    }

    @Test
    void modifyComment() {
        CommentRequestDTO.CommentModifyRequest dto = CommentRequestDTO.CommentModifyRequest.builder()
                .email(LOGIN_ID)
                .password(LOGIN_PASSWORD)
                .content("test")
                        .build();

        commentService.modifyComment(dto, commentId);
        Comment comment = commentRepository.findById(commentId).get();

        assertThat(comment.getContent()).isEqualTo("test");

    }

    @Test
    void withdrawComment() {
        CommentRequestDTO.CommentWithdrawRequest dto = CommentRequestDTO.CommentWithdrawRequest.builder()
                .email(LOGIN_ID)
                .password(LOGIN_PASSWORD)
                .build();

        assertThat(commentRepository.findById(commentId).isPresent()).isTrue();

        commentService.withdrawComment(dto, commentId);
        assertThat(commentRepository.findById(commentId).isEmpty()).isTrue();
    }

    @Test
    void withdrawAllByComment() {
        String testId = "test@test.com";
        String testPassword = "testest";

        UserRequestDTO.UserRegisterRequest userDTO = UserRequestDTO.UserRegisterRequest.builder()
                .username(USERNAME)
                .email(testId)
                .password(testPassword)
                .build();

        UserResponseDTO.UserRegisterResponse response = userService.registerUser(userDTO);
        User user1 = userService.login(testId, testPassword);

        CommentRequestDTO.CommentPostRequest dto1 = CommentRequestDTO.CommentPostRequest.builder()
                .articleId(articleId)
                .email(testId)
                .password(testPassword)
                .content("test1")
                .build();
        CommentResponseDTO.CommentPostResponse response1 = commentService.postComment(dto1);

        CommentRequestDTO.CommentPostRequest dto2 = CommentRequestDTO.CommentPostRequest.builder()
                .articleId(articleId)
                .email(testId)
                .password(testPassword)
                .content("test2")
                .build();
        CommentResponseDTO.CommentPostResponse response2 = commentService.postComment(dto2);


        commentService.withdrawAllByUser(user1);

        Optional<Comment> comment1 = commentRepository.findById(response1.getCommentId());
        assertThat(comment1.isPresent()).isFalse();
        Optional<Comment> comment2 = commentRepository.findById(response2.getCommentId());
        assertThat(comment2.isPresent()).isFalse();
        assertThat(commentRepository.findById(commentId).isPresent()).isTrue();

    }
}