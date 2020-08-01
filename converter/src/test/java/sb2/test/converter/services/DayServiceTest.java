package sb2.test.converter.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import sb2.test.converter.models.Day;
import sb2.test.converter.repositories.DayRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DayServiceTest {
    @MockBean
    private DayRepository dayRepository;

    @MockBean
    private DayService dayService;

    @BeforeEach
    void writeRools() {
        when(dayRepository.existsById(LocalDate.now().minusDays(1))).thenReturn(false);
        when(dayRepository.existsById(LocalDate.now())).thenReturn(true);
        when(dayRepository.findById(LocalDate.now())).thenReturn(Optional.of(new Day()));
    }

    @Test
    void whenGetExistDayThenNoCallFetchAndFillDay() {
        dayService.getById(LocalDate.now());
        // times == 1, cause this method called in preload config
        verify(dayService, times(1)).fetchAndFillDayWithValutes(LocalDate.now());
    }
}