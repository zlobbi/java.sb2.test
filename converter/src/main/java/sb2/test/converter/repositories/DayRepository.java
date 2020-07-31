package sb2.test.converter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sb2.test.converter.models.Day;

import java.time.LocalDate;

public interface DayRepository extends JpaRepository<Day, LocalDate> {

    boolean existsById(LocalDate date);
}
