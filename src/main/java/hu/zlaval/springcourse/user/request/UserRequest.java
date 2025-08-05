package hu.zlaval.springcourse.user.request;

import hu.zlaval.springcourse.user.request.types.Phone;
import hu.zlaval.springcourse.user.request.validator.PasswordMatch;
import hu.zlaval.springcourse.user.request.validator.ValidPassword;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@PasswordMatch
public class UserRequest {

    @NotNull
    @NotBlank
    @Email
    private String email;

    @Size(min = 6, max = 42)
    @ValidPassword
    private String password;

    @Size(min = 6, max = 42)
    @ValidPassword
    private String confirmPassword;

    @Positive
    @Min(0)
    @Max(150)
    private Integer age;

    @NotEmpty
    @Size(min = 2)
    @Valid
    private List<Phone> phones;

}
