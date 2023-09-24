package by.pvt.shaurma.core.repository;

import by.pvt.shaurma.api.dto.GoodRequest;
import by.pvt.shaurma.api.dto.OrderRequest;
import by.pvt.shaurma.core.entity.Good;

import java.util.List;

public interface GoodDao {
    List<Good> getAllGoods();

    void deleteGood(Long id);

    void addGood(Good good);

    Good getGoodById(Long id);

    void update(Good good);
}
