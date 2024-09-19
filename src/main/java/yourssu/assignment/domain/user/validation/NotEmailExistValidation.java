package yourssu.assignment.domain.user.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import yourssu.assignment.domain.user.entity.User;
import yourssu.assignment.domain.user.repository.UserRepository;
import yourssu.assignment.error.code.status.ErrorStatus;

import java.util.Optional;

@RequiredArgsConstructor
public class NotEmailExistValidation implements ConstraintValidator<NotExistEmail, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> user = userRepository.findByEmail(s);
        if(user.isPresent()){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus._EMAIL_CONFLICT.getMessage()).addConstraintViolation();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(NotExistEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }


}
