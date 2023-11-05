package by.pvt.shaurma.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ClientRequest extends UserRequest{
    @NotBlank(message = "Поле должно содержать дату первого посещения")
    private LocalDate firstVisit;
    @NotBlank(message = "Поле должно содержать дату последнего посещения")
    private LocalDate lastVisit;
    @NotBlank(message = "Поле должно содержать номер телефона")
    private String telephone;
    @NotBlank(message = "Поле должно содержать потраченную сумму")
    @PositiveOrZero
    private BigDecimal amountSpent;
}
