package yourssu.assignment.domain.comment.service;

import yourssu.assignment.domain.comment.dto.CommentRequestDTO;
import yourssu.assignment.domain.comment.dto.CommentResponseDTO;
import yourssu.assignment.domain.user.entity.User;


public interface CommentService {

    CommentResponseDTO.CommentPostResponse postComment(
            CommentRequestDTO.CommentPostRequest dto
    );

    CommentResponseDTO.CommentModifyResponse modifyComment(
            CommentRequestDTO.CommentModifyRequest dto, Long CommentId
    );

    void withdrawComment(
            CommentRequestDTO.CommentWithdrawRequest dto, Long CommentId
    );

    void withdrawAllByUser(User user);
}
