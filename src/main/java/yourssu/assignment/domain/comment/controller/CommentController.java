package yourssu.assignment.domain.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yourssu.assignment.domain.comment.dto.CommentRequestDTO;
import yourssu.assignment.domain.comment.dto.CommentResponseDTO;
import yourssu.assignment.domain.comment.service.CommentService;
import yourssu.assignment.domain.comment.validation.ExistComment;

@Validated
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public CommentResponseDTO.CommentPostResponse postComment(
            @RequestBody @Valid CommentRequestDTO.CommentPostRequest dto
    ) {
        return commentService.postComment(dto);
    }

    @PutMapping("/comments/{commentId}")
    public CommentResponseDTO.CommentModifyResponse modifyComment(
            @RequestBody @Valid CommentRequestDTO.CommentModifyRequest dto,
            @PathVariable(name = "commentId") @ExistComment @Valid Long commentId
    ) {
        return commentService.modifyComment(dto, commentId);
    }

    @DeleteMapping("/comments/{commentId}")
    public String deleteComment(
            CommentRequestDTO.CommentWithdrawRequest dto,
            @PathVariable(name = "commentId") @ExistComment @Valid Long commentId
    ) {
        commentService.withdrawComment(dto, commentId);
        return "ok";
    }
}
