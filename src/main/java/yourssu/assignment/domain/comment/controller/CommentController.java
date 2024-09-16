package yourssu.assignment.domain.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yourssu.assignment.domain.comment.dto.CommentRequestDTO;
import yourssu.assignment.domain.comment.dto.CommentResponseDTO;
import yourssu.assignment.domain.comment.service.CommentService;
import yourssu.assignment.domain.comment.validation.ExistComment;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/Comments")
    public CommentResponseDTO.CommentPostResponse postComment(
            @RequestBody @Valid CommentRequestDTO.CommentPostRequest dto
    ) {
        return commentService.postComment(dto);
    }

    @PutMapping("/Comments/{CommentId}")
    public CommentResponseDTO.CommentModifyResponse modifyComment(
            @RequestBody @Valid CommentRequestDTO.CommentModifyRequest dto,
            @PathVariable @ExistComment @Valid Long CommentId
    ) {
        return commentService.modifyComment(dto, CommentId);
    }

    @DeleteMapping("/Comments/{CommentId}")
    public String deleteComment(
            CommentRequestDTO.CommentWithdrawRequest dto,
            @PathVariable @ExistComment @Valid Long CommentId
    ) {
        commentService.withdrawComment(dto, CommentId);
        return "ok";
    }
}
