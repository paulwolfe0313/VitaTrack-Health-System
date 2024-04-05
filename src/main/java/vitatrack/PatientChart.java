package vitatrack;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
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
