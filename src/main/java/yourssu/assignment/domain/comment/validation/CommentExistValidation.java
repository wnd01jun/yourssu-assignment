package yourssu.assignment.domain.comment.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import yourssu.assignment.domain.comment.entity.Comment;
import yourssu.assignment.domain.comment.repository.CommentRepository;
import yourssu.assignment.error.code.status.ErrorStatus;

import java.util.Optional;

@RequiredArgsConstructor
public class CommentExistValidation implements ConstraintValidator<ExistComment, Long> {

    private final CommentRepository commentRepository;

    @Override
    public boolean isValid(Long commentId, ConstraintValidatorContext constraintValidatorContext) {

        Optional<Comment> comment = commentRepository.findById(commentId);

        if(comment.isEmpty()) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus._COMMENT_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }

        return true;
    }

    @Override
    public void initialize(ExistComment constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
