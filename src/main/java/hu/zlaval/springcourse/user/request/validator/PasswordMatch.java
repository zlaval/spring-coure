package hu.zlaval.springcourse.user.request.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {PasswordMatchValidator.class, PatchUserPasswordMatchValidator.class})
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatch {
    String message = "{validation.password.not.match}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
