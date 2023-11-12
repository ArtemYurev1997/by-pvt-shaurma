package by.pvt.shaurma.core.repository;

import by.pvt.shaurma.core.entity.Ingridient;

import java.util.List;

public interface IngridientDao {
    List<Ingridient> getAllIngridients();

    void deleteIngridient(Long id);

    void addIngridient(Ingridient ingridient);

    Ingridient getIngridientById(Long id);

    void update(Ingridient ingridient);
}
