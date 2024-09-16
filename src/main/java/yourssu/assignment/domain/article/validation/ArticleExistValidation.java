package yourssu.assignment.domain.article.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import yourssu.assignment.domain.article.entity.Article;
import yourssu.assignment.domain.article.repository.ArticleRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class ArticleExistValidation implements ConstraintValidator<ExistArticle, Long> {

    private final ArticleRepository articleRepository;

    @Override
    public boolean isValid(Long articleId, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Article> article = articleRepository.findById(articleId);

        if (article.isEmpty()) {
            return false;
        }

        return true;


    }

    @Override
    public void initialize(ExistArticle constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
