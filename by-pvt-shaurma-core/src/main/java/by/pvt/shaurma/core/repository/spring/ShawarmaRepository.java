package by.pvt.shaurma.core.repository.spring;

import by.pvt.shaurma.core.entity.Shawarma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShawarmaRepository extends JpaRepository<Shawarma, Long> {
    @Query("select s from Shawarma s join s.ingridients i where i.name=:name")
    List<Shawarma> getShawarmasByIngridientName(@Param("name") String name);
}
