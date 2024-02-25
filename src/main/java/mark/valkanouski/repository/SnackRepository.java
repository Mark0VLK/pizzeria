package mark.valkanouski.repository;

import mark.valkanouski.entity.Snack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnackRepository extends JpaRepository<Snack, Long> {
}
