package sb2.test.converter.services;

import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import sb2.test.converter.models.Day;
import sb2.test.converter.models.Valute;
import sb2.test.converter.repositories.DayRepository;
import sb2.test.converter.repositories.ValuteRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class DayService {
    private DayRepository dayRepository;
    private ValuteRepository valuteRepository;

    public Day getById(LocalDate date) {
        if (!dayRepository.existsById(date)) {
            fetchAndFillDayWithValutes(date);
        }
        return dayRepository.findById(date).get();
    }

    public void fetchAndFillDayWithValutes(LocalDate date) {
        dayRepository.save(new Day(date));
        try {
            // cbr link to get data
            var URL = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            Elements elements = Jsoup.connect(URL).ignoreContentType(true).get().getElementsByTag("Valute");
            Valute v;

            // loop for all available valutes
            for (Element e : elements) {
                v = new Valute();
                v.setNumCode(e.getElementsByTag("NumCode").get(0).text());
                v.setCharCode(e.getElementsByTag("CharCode").get(0).text());
                v.setNominal(Integer.parseInt(e.getElementsByTag("Nominal").get(0).text()));
                v.setName(e.getElementsByTag("Name").get(0).text());
                v.setValue(Float.parseFloat(e.getElementsByTag("Value").get(0).text().replace(",", ".")));
                v.setDay(dayRepository.findById(date).get());
                valuteRepository.save(v);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
