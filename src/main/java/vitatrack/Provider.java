package vitatrack;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Provider extends Users{

    String medicalLicenseNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "provider", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<Appointment> appointment;

    @JsonIgnore
    @OneToMany(mappedBy = "provider", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<PatientChart> patientChart;

}
