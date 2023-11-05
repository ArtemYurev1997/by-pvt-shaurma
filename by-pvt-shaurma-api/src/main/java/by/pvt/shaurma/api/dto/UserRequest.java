package by.pvt.shaurma.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "Поле должно содержать имя")
    private String name;
    @NotBlank(message = "Поле должно содержать фамилию")
    private String surname;
    @NotBlank(message = "Поле должно содержать логин")
    private String login;
    @NotBlank(message = "Поле должно содержать пароль")
    private String password;
    private String role;
}
