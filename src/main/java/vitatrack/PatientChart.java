package vitatrack;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class PatientChart {

    @Id
    @GeneratedValue
    private Long id;

    private Integer height;
    private Integer weight;
    private Integer bloodPressureSystolic;
    private Integer bloodPressureDiastolic;
    private Integer restingHeartRate;

    @OneToOne
    private Appointment appointment;

    @ManyToOne
    private Provider provider;

    @ManyToOne
    private Patient patient;

    @OneToOne
    private Bill bill;

}
