package vitatrack;

import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Provider extends User{

    String medicalLicenseNumber;

    @OneToMany(mappedBy = "provider")
    private Appointment appointment;

    @OneToMany(mappedBy = "provider")
    private PatientChart patientChart;

}
