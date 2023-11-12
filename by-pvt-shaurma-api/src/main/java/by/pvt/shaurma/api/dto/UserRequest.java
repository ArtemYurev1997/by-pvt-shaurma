package by.pvt.shaurma.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserRequest {
    private Long id;
    @NotBlank(message = "Поле должно содержать имя")
    @Length(max = 30, message = "Недостаточно или много символов")
    private String name;
    @NotBlank(message = "Поле должно содержать фамилию")
    @Length(max = 50, message = "Недостаточно или много символов")
    private String surname;
    @NotBlank(message = "Поле должно содержать логин")
    @Length(min = 4, max = 16, message = "Недостаточно или много символов")
    private String login;
    @NotBlank(message = "Поле должно содержать пароль")
    @Length(min = 4, max = 16, message = "Недостаточно или много символов")
    private String password;
    private String role;
}
