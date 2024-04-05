package vitatrack;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Patient extends User{


    @CreditCardNumber(message="Not a valid credit card number")
    private String paymentCardNumber;
    @Pattern(regexp="^(0[1-9]|1[0-2]) ([\\/]) ([2-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    private String insuranceProvider;
    private String insuranceNumber;

    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<Appointment> appointment;

    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<PatientChart> patientChart;


}
