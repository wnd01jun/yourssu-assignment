package yourssu.assignment.domain.comment.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import yourssu.assignment.domain.article.validation.ArticleExistValidation;

import java.lang.annotation.*;
@Documented
@Constraint(validatedBy = CommentExistValidation.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistComment {
    String message() default "댓글이 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

