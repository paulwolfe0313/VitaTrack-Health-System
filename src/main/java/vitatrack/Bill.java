package vitatrack;

import jakarta.persistence.*;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Bill {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "bill")
    private PatientChart patientChart;

    @ManyToOne
    private Admin admin;
}
