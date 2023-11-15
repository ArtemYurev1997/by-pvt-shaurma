package by.pvt.shaurma.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AdminRequest extends UserRequest{
    @NotNull(message = "Поле должно содержать дату входа")
    private LocalDate dateEnter;
    private LocalDate dateExit;
    @NotBlank(message = "Поле должно содержать должность")
    private String post;
    @NotNull(message = "Поле должно содержать значение зарплаты")
    @PositiveOrZero(message = "Не отрицательное значение")
    private BigDecimal salary;
}
