package vitatrack;

import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Patient extends User{


    @CreditCardNumber(message="Not a valid credit card number")
    private String paymentCardNumber;
    @Pattern(regexp="^(0[1-9]|1[0-2]) ([\\/]) ([2-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    private String insuranceProvider;
    private String insuranceNumber;

    @OneToMany(mappedBy = "patient")
    private Appointment appointment;

    @OneToMany(mappedBy = "patient")
    private PatientChart patientChart;


}
