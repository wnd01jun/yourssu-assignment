package yourssu.assignment.domain.user.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotEmailExistValidation.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotExistEmail {
    String message() default "이메일이 존재합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
