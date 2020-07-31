package sb2.test.converter.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import sb2.test.converter.models.Exchange;
import sb2.test.converter.models.Valute;
import sb2.test.converter.repositories.ExchangeRepository;
import sb2.test.converter.repositories.ValuteRepository;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ExchangeService {
    private ExchangeRepository exchangeRepository;
    private ValuteRepository valuteRepository;

    public List<Exchange> getAllByUserName(String name) {
        return exchangeRepository.findAllByUserNameOrderByIdDesc(name);
    }

    public List<Exchange> getFilteredExchanges(Map<String, Object> params, Principal principal) {
        var ex = new Exchange();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("initSum", "resultSum", "id")
                .withIgnoreNullValues();

        ex.setUserName(principal.getName());
        if (!params.get("date").toString().isBlank()) {
            try {
                LocalDate date = LocalDate.parse(params.get("date").toString());
                ex.setDate(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        if (params.get("fromVal") != null) {
            var fromVal = valuteRepository.findById(Integer.parseInt((String) params.get("fromVal"))).get();
            ex.setFromValute(fromVal.getCharCode() + " (" + fromVal.getName() + ")");
        }

        if (params.get("toVal") != null) {
            var toVal = valuteRepository.findById(Integer.parseInt((String) params.get("toVal"))).get();
            ex.setToValute(toVal.getCharCode() + " (" + toVal.getName() + ")");
        }

        Example example = Example.of(ex, matcher);
        return exchangeRepository.findAll(example);
    }


    public float createAndSaveExchange(Map<String, String> params, Principal principal) {
        Valute fromValute = valuteRepository.findById(Integer.parseInt(params.get("fromValute"))).get();
        Valute toValute = valuteRepository.findById(Integer.parseInt(params.get("toValute"))).get();
        float initSum = Float.parseFloat(params.get("initSum"));
        float resultSum = ((fromValute.getValue() / fromValute.getNominal()) * initSum) / (toValute.getValue() / toValute.getNominal());
        String userName = principal.getName();
        var ex = new Exchange();
        ex.setFromValute(fromValute.getCharCode() + " (" + fromValute.getName() + ")");
        ex.setToValute(toValute.getCharCode() + " (" + toValute.getName() + ")");
        ex.setInitSum(initSum);
        ex.setResultSum(resultSum);
        ex.setUserName(userName);
        ex.setDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        exchangeRepository.save(ex);
        return resultSum;
    }
}
