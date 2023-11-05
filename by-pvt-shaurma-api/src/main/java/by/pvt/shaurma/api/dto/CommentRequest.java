package by.pvt.shaurma.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
public class CommentRequest {
    @Length(min = 1, max = 1000, message = "Много или мало символов")
    private String comment;
    @NotBlank
    private LocalDateTime date;

}
