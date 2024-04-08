package vitatrack;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String prescriptionName;
    private BigDecimal prescriptionCost;
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "prescriptions", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<PatientChart> patientChart;
}
