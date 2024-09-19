package yourssu.assignment.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import yourssu.assignment.domain.article.entity.Article;
import yourssu.assignment.domain.basic.BaseEntity;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Article> articles;
}
