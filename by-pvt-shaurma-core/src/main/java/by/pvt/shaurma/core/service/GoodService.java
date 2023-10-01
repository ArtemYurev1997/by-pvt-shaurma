package by.pvt.shaurma.core.service;

import by.pvt.shaurma.api.contract.GoodApi;
import by.pvt.shaurma.api.dto.GoodRequest;
import by.pvt.shaurma.api.dto.GoodResponse;
import by.pvt.shaurma.core.entity.Good;
import by.pvt.shaurma.core.mapper.GoodMapper;
import by.pvt.shaurma.core.repository.GoodDao;

import java.util.List;
import java.util.stream.Collectors;

public class GoodService implements GoodApi {
    private final GoodDao goodDao;
    private final GoodMapper goodMapper;

    public GoodService(GoodDao goodDao, GoodMapper goodMapper) {
        this.goodDao = goodDao;
        this.goodMapper = goodMapper;
    }

    @Override
    public List<GoodResponse> findAllProducts() {
        return goodDao.getAllGoods().stream().map(goodMapper::mapToGoodDto).collect(Collectors.toList());
    }

    @Override
    public void addGood(GoodRequest goodRequest) {
        Good good = new Good(goodRequest.getName(), goodRequest.getCode(), goodRequest.getDescription(), goodRequest.getPrice());
        goodDao.addGood(good);
    }

    @Override
    public void deleteGood(Long id) {
        goodDao.deleteGood(id);
    }

    @Override
    public GoodResponse findGoodById(Long id) {
        return goodMapper.mapToGoodDto(goodDao.getGoodById(id));
    }

    @Override
    public List<GoodResponse> update(GoodRequest goodRequest) {
        goodDao.update(goodMapper.mapToGoodEntity(goodRequest));
        return findAllProducts();
    }
}
