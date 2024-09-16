package yourssu.assignment.domain.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yourssu.assignment.domain.article.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
