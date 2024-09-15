package yourssu.assignment.domain.user.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import yourssu.assignment.domain.user.entity.User;
import yourssu.assignment.domain.user.repository.UserRepository;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class EmailExistValidation implements ConstraintValidator<ExistEmail, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
//        if(s != null || s.length() <= 1) {
//            return false;
//        }
//        if(!validate(s)) {
//            return false;
//        }
        Optional<User> user = userRepository.findByEmail(s);
        if(user.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public void initialize(ExistEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

//    private Pattern VALID_EMAIL_ADDRESS_REGEX =
//            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
//
//    private boolean validate(String email) {
//        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
//        return matcher.matches();
//    }
}
