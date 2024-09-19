package yourssu.assignment.domain.user.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import yourssu.assignment.domain.user.entity.User;
import yourssu.assignment.domain.user.repository.UserRepository;
import yourssu.assignment.error.code.status.ErrorStatus;

import java.util.Optional;

@RequiredArgsConstructor
public class EmailExistValidation implements ConstraintValidator<ExistEmail, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        Optional<User> user = userRepository.findByEmail(s);
        if(user.isEmpty()){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus._EMAIL_NOT_FOUND.getMessage()).addConstraintViolation();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(ExistEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }


}
