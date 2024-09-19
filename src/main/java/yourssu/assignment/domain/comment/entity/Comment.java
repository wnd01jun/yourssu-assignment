package yourssu.assignment.domain.comment.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import yourssu.assignment.domain.article.entity.Article;
import yourssu.assignment.domain.basic.BaseEntity;
import yourssu.assignment.domain.comment.dto.CommentRequestDTO;
import yourssu.assignment.domain.user.entity.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    public void updateComment(CommentRequestDTO.CommentModifyRequest dto) {
        content = dto.getContent();
    }


}
