package ecommerce.v1.models.dto.user.authentication;

import lombok.Getter;
import lombok.Setter;

public class UsersDto {
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String password;
}
