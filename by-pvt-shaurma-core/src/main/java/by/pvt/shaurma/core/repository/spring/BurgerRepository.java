package by.pvt.shaurma.core.repository.spring;

import by.pvt.shaurma.core.entity.Burger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BurgerRepository extends JpaRepository<Burger, Long> {
    @Query("select b from Burger b join b.ingridients i where i.name=:name")
    List<Burger> getBurgersByIngridientName(@Param("name") String name);
}
