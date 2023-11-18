package by.pvt.shaurma.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShawarmaCreateRequest {
    private ShawarmaRequest shawarmaRequest;
    private IngridientDto ingridientDto1;
    private IngridientDto ingridientDto2;
}
