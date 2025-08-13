package hu.zlaval.springcourse.user.request.validator;

import hu.zlaval.springcourse.user.request.PatchUserPasswordRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PatchUserPasswordMatchValidator implements ConstraintValidator<PasswordMatch, PatchUserPasswordRequest> {
    @Override
    public boolean isValid(PatchUserPasswordRequest value, ConstraintValidatorContext context) {
        return value != null && value.getPassword().equals(value.getConfirmPassword());
    }
}
