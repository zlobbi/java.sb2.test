package sb2.test.converter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sb2.test.converter.models.Exchange;

import java.util.List;

public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {
    List<Exchange> findAllByUserNameOrderByIdDesc(String name);
}
