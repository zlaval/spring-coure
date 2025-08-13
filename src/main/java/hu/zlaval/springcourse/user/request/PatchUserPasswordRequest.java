package hu.zlaval.springcourse.user.request;

import hu.zlaval.springcourse.user.request.validator.PasswordMatch;
import hu.zlaval.springcourse.user.request.validator.ValidPassword;
import lombok.Data;

@PasswordMatch
@Data
public class PatchUserPasswordRequest {

    @ValidPassword
    private String password;

    @ValidPassword
    private String confirmPassword;
}
