package by.pvt.shaurma.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class OrderRequest {
    private Long id;
    @NotNull
    private ClientRequest userId;
    @NotNull(message = "Введите число")
    private Long count;
    @NotNull(message = "Введите число")
    @PositiveOrZero(message = "Вы ввели отрицательное значение")
    private BigDecimal cost;
    @NotNull(message = "Введите адрес")
    private String address;
    @NotNull(message = "Введите номер телефона")
    private String telephone;
    @NotNull
    private String comment;
    @NotNull(message = "Введите дату")
    private LocalDate date;
    @NotBlank(message = "Введите статус заказа")
    private String status;
    @NotBlank(message = "Введите статус оплаты")
    private String payment;
}
