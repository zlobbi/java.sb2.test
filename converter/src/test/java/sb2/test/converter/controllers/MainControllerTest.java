package sb2.test.converter.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import sb2.test.converter.models.Day;
import sb2.test.converter.models.Exchange;
import sb2.test.converter.models.Valute;
import sb2.test.converter.services.DayService;
import sb2.test.converter.services.ExchangeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private MainController mainController;

    @MockBean
    private DayService dayService;

    @MockBean
    private ExchangeService exchangeService;


    private Day day;
    private List<Valute> valutes = new ArrayList<>();
    private List<Exchange> exchanges = new ArrayList<>();

    @Test
    public void contextLoads() {
        assertThat(mainController).isNotNull();
    }

    @BeforeEach
    void initAddresses(){
        day = new Day(LocalDate.now());
        valutes.add(new Valute(1, "numcode", "charcode", 1, "usd", 2.2f, day));
        day.setValutes(valutes);
        exchanges.add(new Exchange(1, "user", "datefrom", "dateto", 2.2f, 2.2f, "date"));
    }

    @Test
    @WithMockUser("user")
    public void shouldProvideConverterPageWithData() throws Exception {
        when(dayService.getById(LocalDate.now())).thenReturn(day);
        when(exchangeService.getAllByUserName("user")).thenReturn(exchanges);

        this.mvc.perform(get("/"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(view().name("converter"))
                .andExpect(model().attribute("day", is(day)))
                .andExpect(model().attribute("user", is("user")))
                .andExpect(model().attribute("exchangeHistory", hasSize(1)));

        verify(dayService, times(1)).getById(LocalDate.now());
        verify(exchangeService, times(1)).getAllByUserName("user");

    }
}