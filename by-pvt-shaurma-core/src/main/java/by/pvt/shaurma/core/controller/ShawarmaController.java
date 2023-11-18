package by.pvt.shaurma.core.controller;

import by.pvt.shaurma.api.contract.ShawarmaApi;
import by.pvt.shaurma.api.dto.IngridientDto;
import by.pvt.shaurma.api.dto.ShawarmaCreateRequest;
import by.pvt.shaurma.api.dto.ShawarmaDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("shawarmas")
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
    ShawarmaDto createShawarma(@RequestBody ShawarmaCreateRequest shawarmaCreateRequest) {
        String type = shawarmaCreateRequest.getShawarmaRequest().getType();
        Long code = shawarmaCreateRequest.getShawarmaRequest().getCode();
        Long start = shawarmaCreateRequest.getIngridientDto1().getId();
        Long end = shawarmaCreateRequest.getIngridientDto2().getId();
        return shawarmaApi.createShawarma(start, end, type, code);
    }
}
