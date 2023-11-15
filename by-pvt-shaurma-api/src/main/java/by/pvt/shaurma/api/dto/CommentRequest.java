package by.pvt.shaurma.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDate;

@Data
public class CommentRequest {
    @Length(min = 1, max = 500, message = "Много или мало символов")
    private String comment;
    @NotNull
    private LocalDate date;
    @NotNull
    private Long clientId;

}
