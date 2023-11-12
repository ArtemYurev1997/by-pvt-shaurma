package by.pvt.shaurma.core.controller;

import by.pvt.shaurma.api.contract.ShawarmaApi;
import by.pvt.shaurma.api.dto.IngridientDto;
import by.pvt.shaurma.api.dto.ShawarmaDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("shawarma")
public class ShawarmaController {
    private final ShawarmaApi shawarmaApi;

    public ShawarmaController(@Qualifier("shawarmaServiceApi") ShawarmaApi shawarmaApi) {
        this.shawarmaApi = shawarmaApi;
    }

    @PostMapping("/searchForShawarmaName")
    List<ShawarmaDto> getShawarmaDtoByIngridient(@RequestBody IngridientDto ingridientDto) {
        String name = ingridientDto.getName();
        return shawarmaApi.getShawarmaDtoByIngridient(name);
    }

    @PostMapping("/createShawarma")
    ShawarmaDto createShawarma(@RequestBody ShawarmaDto shawarmaDto, Long start, Long end) {
        Long id = shawarmaDto.getId();
        String type = shawarmaDto.getType();
        Long code = shawarmaDto.getCode();
        return shawarmaApi.createShawarma(id, start, end, type, code);
    }
}
