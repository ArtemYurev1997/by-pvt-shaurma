package by.pvt.shaurma.core.repository;

import by.pvt.shaurma.core.entity.Burger;


import java.util.List;

public interface BurgerDao {
    List<Burger> getAllBurgers();

    void deleteBurger(Long id);

    void addBurger(Burger burger);

    Burger getBurgerById(Long id);

    void update(Burger burger);
}
