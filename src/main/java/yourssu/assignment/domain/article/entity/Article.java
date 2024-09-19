package yourssu.assignment.domain.article.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import yourssu.assignment.domain.article.dto.ArticleRequestDTO;
import yourssu.assignment.domain.basic.BaseEntity;
import yourssu.assignment.domain.comment.entity.Comment;
import yourssu.assignment.domain.user.entity.User;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
public class Article extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "article_id")
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "article", orphanRemoval = true)
    private List<Comment> comments;

    public void updateArticle(ArticleRequestDTO.ArticleModifyRequest dto) {
        title = dto.getTitle();
        content = dto.getContent();
    }
}
