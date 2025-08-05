package hu.zlaval.springcourse.user.request.types;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record Phone(
        @NotNull
        @Size(min = 6)
        String phoneNumber
) {
}
