package sb2.test.converter.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sb2.test.converter.models.Day;
import sb2.test.converter.repositories.DayRepository;
import sb2.test.converter.services.DayService;

import java.time.LocalDate;

@Configuration
public class PreloadCurrencyData {

    @Bean
    CommandLineRunner fetchData(DayRepository dayRepository, DayService dayService) {
        return args -> {

            if (!dayRepository.existsById(LocalDate.now())) {
                dayRepository.save(new Day(LocalDate.now()));
            }
            dayService.fetchAndFillDayWithValutes(LocalDate.now());

        };
    }
}

