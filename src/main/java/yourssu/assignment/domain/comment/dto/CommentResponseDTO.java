package yourssu.assignment.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;

public class CommentResponseDTO {

    @Getter
    @Builder
    public static class CommentPostResponse {

        private Long commentId;

        private String email;

        private String content;
    }

    @Getter
    @Builder
    public static class CommentModifyResponse {

        private Long commentId;

        private String email;

        private String content;
    }
}
