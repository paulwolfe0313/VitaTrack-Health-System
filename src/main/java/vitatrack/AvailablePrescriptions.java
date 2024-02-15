package vitatrack;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Data
public class AvailablePrescriptions {

    @Id
    @GeneratedValue
    private Long id;

    private String prescriptionName;
    private BigDecimal prescriptionCost;
    private String description;

    @ManyToMany
    private PatientChart patientChart;
}
