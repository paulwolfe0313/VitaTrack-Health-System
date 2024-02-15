package vitatrack;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

import javax.persistence.Entity;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
public class Appointment {

    @Id
    @GeneratedValue
    private Long id;

    private Date appointmentDate;

    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    private Provider provider;

    @ManyToOne
    private Patient patient;

    @OneToOne
    private PatientChart patientChart;
}
