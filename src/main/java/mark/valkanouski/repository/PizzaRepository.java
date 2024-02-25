package mark.valkanouski.repository;

import mark.valkanouski.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
