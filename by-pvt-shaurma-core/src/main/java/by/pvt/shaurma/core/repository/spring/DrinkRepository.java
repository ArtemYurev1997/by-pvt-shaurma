package by.pvt.shaurma.core.repository.spring;


import by.pvt.shaurma.core.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
}
