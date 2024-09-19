package yourssu.assignment.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yourssu.assignment.domain.article.entity.Article;
import yourssu.assignment.domain.article.service.ArticleService;
import yourssu.assignment.domain.comment.coverter.CommentConverter;
import yourssu.assignment.domain.comment.dto.CommentRequestDTO;
import yourssu.assignment.domain.comment.dto.CommentResponseDTO;
import yourssu.assignment.domain.comment.entity.Comment;
import yourssu.assignment.domain.comment.repository.CommentRepository;
import yourssu.assignment.domain.user.entity.User;
import yourssu.assignment.domain.user.service.UserService;
import yourssu.assignment.error.code.status.ErrorStatus;
import yourssu.assignment.error.exception.GeneralException;


@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ArticleService articleService;

    @Override
    public CommentResponseDTO.CommentPostResponse postComment(CommentRequestDTO.CommentPostRequest dto) {
        Article article = articleService.findArticle(dto.getArticleId());
        User user = userService.login(dto.getEmail(), dto.getPassword());
        Comment comment = CommentConverter.postToComment(dto, user, article);
        commentRepository.save(comment);
        return CommentConverter.commentToPost(comment);
    }

    @Override
    public CommentResponseDTO.CommentModifyResponse modifyComment(CommentRequestDTO.CommentModifyRequest dto, Long commentId) {
        Comment comment = findComment(commentId);
        loginAndModifyComment(dto, comment);
        return CommentConverter.commentToModify(comment);


    }

    private void loginAndModifyComment(CommentRequestDTO.CommentModifyRequest dto, Comment comment) {
        if(checkMasterOfComment(comment, dto.getEmail(), dto.getPassword())) {
            comment.updateComment(dto);
            return;
        }
        throw new GeneralException(ErrorStatus._UNAUTHORIZED);
    }

    private boolean checkMasterOfComment(Comment comment, String email, String password) {
        User user = userService.login(email, password);
        return user.getId().equals(comment.getUser().getId());

    }

    private Comment findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new GeneralException(ErrorStatus._COMMENT_NOT_FOUND));
    }


    @Override
    public void withdrawComment(CommentRequestDTO.CommentWithdrawRequest dto, Long commentId) {
        Comment comment = findComment(commentId);
        loginAndWithdrawComment(dto, comment);
    }

    private void loginAndWithdrawComment(CommentRequestDTO.CommentWithdrawRequest dto, Comment comment) {
        if(checkMasterOfComment(comment, dto.getEmail(), dto.getPassword())) {
            commentRepository.delete(comment);
            return;
        }
        throw new GeneralException(ErrorStatus._UNAUTHORIZED);
    }

    @Override
    public void withdrawAllByUser(User user) {
        commentRepository.deleteAllByUser(user);
    }
}
