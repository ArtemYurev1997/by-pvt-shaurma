package by.pvt.shaurma.core.controller;

import by.pvt.shaurma.api.contract.GoodApi;
import by.pvt.shaurma.api.dto.GoodRequest;
import by.pvt.shaurma.api.dto.GoodResponse;

import java.util.List;

public class GoodController {
    private final GoodApi goodApi;

    public GoodController(GoodApi goodApi) {
        this.goodApi = goodApi;
    }

    public List<GoodResponse> findAllProducts() {
        return goodApi.findAllProducts();
    }

    public void addGood(GoodRequest goodRequest) {
        goodApi.addGood(goodRequest);
    }

    public void deleteGood(Long id) {
        goodApi.deleteGood(id);
    }

    public GoodResponse findGoodById(Long id) {
        return goodApi.findGoodById(id);
    }

    public List<GoodResponse> update(GoodRequest goodRequest) {
        return goodApi.update(goodRequest);
    }
}
