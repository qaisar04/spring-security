package kz.baltabayev.springsecurityexample.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    private String name;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}
