package by.pvt.shaurma.api.contract;

import by.pvt.shaurma.api.dto.ShawarmaDto;

import java.util.List;

public interface ShawarmaApi {
    List<ShawarmaDto> getShawarmaDtoByIngridient(String name);

    ShawarmaDto createShawarma(Long id, Long start, Long end, String type, Long code);
}
