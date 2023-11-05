package by.pvt.shaurma.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AdminRequest extends UserRequest{
    @NotBlank(message = "Поле должно содержать дату входа")
    private LocalDate dateEnter;
    @NotBlank(message = "Поле должно содержать дату выхода")
    private LocalDate dateExit;
    @NotBlank(message = "Поле должно содержать должность")
    private String post;
    @NotBlank(message = "Поле должно содержать значение зарплаты")
    @PositiveOrZero
    private BigDecimal salary;
}
