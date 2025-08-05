package hu.zlaval.springcourse.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEntity {

    private Long id;

    private String email;

    private String password;

}
