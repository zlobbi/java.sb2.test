package sb2.test.converter.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "exchanges")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exchange {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String fromValute;
    private String toValute;
    private float initSum;
    private float resultSum;
    private String date;
}
