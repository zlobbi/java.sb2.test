package sb2.test.converter.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "valutes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Valute {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numCode;
    private String charCode;
    private int nominal;
    private String name;
    private Float value;
    @ManyToOne
    @JoinColumn(name = "day_id")
    private Day day;

}
