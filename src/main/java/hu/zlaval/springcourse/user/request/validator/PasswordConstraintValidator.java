package hu.zlaval.springcourse.user.request.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.LengthRule;

public class PasswordConstraintValidator  implements ConstraintValidator<ValidPassword, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        var validator = new PasswordValidator(new LengthRule(4, 20));
        var result = validator.validate(new PasswordData(value));
        return result.isValid();
    }
}
