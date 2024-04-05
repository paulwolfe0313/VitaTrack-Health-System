package vitatrack;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class AvailablePrescriptions {

    @Id
    @GeneratedValue
    private Long id;

    private String prescriptionName;
    private BigDecimal prescriptionCost;
    private String description;

    @ManyToMany
    private List<PatientChart> patientChart;
}
