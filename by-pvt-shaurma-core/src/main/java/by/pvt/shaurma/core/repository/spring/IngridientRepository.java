package by.pvt.shaurma.core.repository.spring;

import by.pvt.shaurma.core.entity.Ingridient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface IngridientRepository extends JpaRepository<Ingridient, Long> {
    @Query("select i from Ingridient i where i.id>=:start and i.id<=:end")
    List<Ingridient> selectIngridientsForCreate(@Param("start") Long start, @Param("end") Long end);

    @Modifying
    @Query("update Ingridient i set i.total=i.total-:decrement where i.id>=:start and i.id<=:end")
    void updateCountIngridients(@Param("decrement") Long decrement,@Param("start") Long start, @Param("end") Long end); //???

    @Query("select sum(i.price) from Ingridient i where i.id>=:start and i.id<=:end")
    BigDecimal getSumOfIngridients(@Param("start") Long start, @Param("end") Long end);
}
