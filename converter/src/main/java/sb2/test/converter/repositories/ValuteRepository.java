package sb2.test.converter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import sb2.test.converter.models.Valute;

public interface ValuteRepository extends JpaRepository<Valute, Integer> {
}
