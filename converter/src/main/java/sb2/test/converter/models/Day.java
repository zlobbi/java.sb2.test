package sb2.test.converter.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "days")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Day {
    @Id
    private LocalDate id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "day")
    private List<Valute> valutes;

    public Day(LocalDate date) {
        this.id = date;
    }
}
