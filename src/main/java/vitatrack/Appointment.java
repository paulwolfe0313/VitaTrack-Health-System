package vitatrack;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.Date;

import static jakarta.persistence.TemporalType.DATE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date appointmentDate;

    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Provider provider;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Patient patient;

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private PatientChart patientChart;
}
