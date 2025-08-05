package hu.zlaval.springcourse.user.request.validator;

import hu.zlaval.springcourse.user.request.UserRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

//One implementation for every request objects or superclass
//Constraint annotation supports multiple validator by the array field
public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserRequest> {
    @Override
    public boolean isValid(UserRequest value, ConstraintValidatorContext context) {
        return value != null && value.getPassword().equals(value.getConfirmPassword());
    }
}
