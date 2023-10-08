package by.pvt.shaurma.core.repository;


import by.pvt.shaurma.core.entity.Shawarma;

import java.util.List;

public interface ShawarmaDao {
    List<Shawarma> getAllShawarmas();

    void deleteShawarma(Long id);

    void addShawarma(Shawarma shawarma);

    Shawarma getShawarmaById(Long id);

    void update(Shawarma shawarma);
}
