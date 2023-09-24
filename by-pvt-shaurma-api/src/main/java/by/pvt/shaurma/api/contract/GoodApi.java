package by.pvt.shaurma.api.contract;

import by.pvt.shaurma.api.dto.GoodRequest;
import by.pvt.shaurma.api.dto.GoodResponse;

import java.util.List;

public interface GoodApi {
    List<GoodResponse> findAllProducts();

    void addGood(GoodRequest goodRequest);

    void deleteGood(Long id);

    GoodResponse findGoodById(Long id);

    List<GoodResponse> update(GoodRequest goodRequest);
}
