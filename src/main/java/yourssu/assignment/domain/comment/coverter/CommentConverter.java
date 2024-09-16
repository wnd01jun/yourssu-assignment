package yourssu.assignment.domain.comment.coverter;

import yourssu.assignment.domain.article.entity.Article;
import yourssu.assignment.domain.comment.dto.CommentRequestDTO;
import yourssu.assignment.domain.comment.dto.CommentResponseDTO;
import yourssu.assignment.domain.comment.entity.Comment;
import yourssu.assignment.domain.user.entity.User;

public class CommentConverter {

    public static Comment postToComment(CommentRequestDTO.CommentPostRequest dto, User user, Article article) {
        return Comment.builder()
                .user(user)
                .content(dto.getContent())
                .article(article)
                .build();
    }

    public static CommentResponseDTO.CommentPostResponse commentToPost(Comment Comment) {
        return CommentResponseDTO.CommentPostResponse.builder()
                .commentId(Comment.getId())
                .email(Comment.getUser().getEmail())
                .content(Comment.getContent())
                .build();
    }

    public static CommentResponseDTO.CommentModifyResponse commentToModify(Comment Comment) {
        return CommentResponseDTO.CommentModifyResponse.builder()
                .commentId(Comment.getId())
                .email(Comment.getUser().getEmail())
                .content(Comment.getContent())
                .build();
    }
}
