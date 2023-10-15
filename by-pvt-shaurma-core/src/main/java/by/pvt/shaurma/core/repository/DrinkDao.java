package by.pvt.shaurma.core.repository;

import by.pvt.shaurma.core.entity.Drink;
import by.pvt.shaurma.core.entity.Shawarma;

import java.util.List;

public interface DrinkDao {
    List<Drink> getAllDrinks();

    void deleteDrink(Long id);

    void addDrink(Drink drink);

    Drink getDrinkById(Long id);

    void update(Drink drink);
}
