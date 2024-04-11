package vitatrack;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import vitatrack.data.AvailablePrescriptionsRepository;
import vitatrack.data.AvailableProceduresRepository;
import vitatrack.data.PatientRepository;
import vitatrack.data.ProviderRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@SpringBootApplication
public class VitaTrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(VitaTrackApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(AvailableProceduresRepository availableProceduresRepository,
                                        AvailablePrescriptionsRepository availablePrescriptionsRepository,
                                        PatientRepository patientRepository,
                                        ProviderRepository providerRepository) {
      
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

                Provider provider1 = new Provider();
                provider1.setUserName("prov");
                provider1.setPassWord("password");
                provider1.setFirstName("George");
                provider1.setLastName("Jacobs");
                provider1.setMedicalLicenseNumber("12345678");
                providerRepository.save(provider1);

                Provider provider2 = new Provider();
                provider2.setUserName("prov2");
                provider2.setPassWord("password");
                provider2.setFirstName("Phil");
                provider2.setLastName("Donaldson");
                provider2.setMedicalLicenseNumber("87654321");
                providerRepository.save(provider2);

                Patient patient1 = new Patient();
                patient1.setUserName("patient");
                patient1.setPassWord("password");
                patient1.setFirstName("Paul");
                patient1.setLastName("Daniels");
                patient1.setPaymentCardNumber("4111111111111111");
                patient1.setCcCVV("123");
                patient1.setCcExpiration("1126");
                patient1.setInsuranceNumber("12345678");
                patient1.setInsuranceProvider("MedCo");
                patientRepository.save(patient1);

                Patient patient2 = new Patient();
                patient2.setUserName("patient2");
                patient2.setPassWord("password");
                patient2.setFirstName("Kelly");
                patient2.setLastName("Fisher");
                patient2.setPaymentCardNumber("4111111111111111");
                patient2.setCcCVV("123");
                patient2.setCcExpiration("1127");
                patient2.setInsuranceNumber("87654321");
                patient2.setInsuranceProvider("MedCo");
                patientRepository.save(patient2);

                AvailableProcedures checkUp = new AvailableProcedures();
                checkUp.setProcedureName("Check Up");
                checkUp.setDescription("Regular Wellness Assessment");
                checkUp.setProcedureCost(BigDecimal.valueOf(100.00));
                availableProceduresRepository.save(checkUp);

                AvailableProcedures xRay = new AvailableProcedures();
                xRay.setProcedureName("X Ray");
                xRay.setDescription("X Ray Imaging");
                xRay.setProcedureCost(BigDecimal.valueOf(500.00));
                availableProceduresRepository.save(xRay);

                AvailableProcedures ekg = new AvailableProcedures();
                ekg.setProcedureName("EKG");
                ekg.setDescription("EKG Heart Exam");
                ekg.setProcedureCost(BigDecimal.valueOf(200.00));
                availableProceduresRepository.save(ekg);

                AvailableProcedures strepTest = new AvailableProcedures();
                strepTest.setProcedureName("Strep Test");
                strepTest.setDescription("Strep Throat Test");
                strepTest.setProcedureCost(BigDecimal.valueOf(50.00));
                availableProceduresRepository.save(strepTest);

                AvailableProcedures fluTest = new AvailableProcedures();
                fluTest.setProcedureName("Flu Test");
                fluTest.setDescription("Flu Test");
                fluTest.setProcedureCost(BigDecimal.valueOf(50.00));
                availableProceduresRepository.save(fluTest);

                AvailableProcedures bloodWork = new AvailableProcedures();
                bloodWork.setProcedureName("Blood Work");
                bloodWork.setDescription("Blood Test to check vital levels");
                bloodWork.setProcedureCost(BigDecimal.valueOf(150.00));
                availableProceduresRepository.save(bloodWork);

                AvailablePrescriptions penicillin = new AvailablePrescriptions();
                penicillin.setPrescriptionName("Penicillin");
                penicillin.setDescription("Antibiotic");
                availablePrescriptionsRepository.save(penicillin);

                AvailablePrescriptions celebrex = new AvailablePrescriptions();
                celebrex.setPrescriptionName("Celebrex");
                celebrex.setDescription("Anti-inflammatory");
                availablePrescriptionsRepository.save(celebrex);

                AvailablePrescriptions ibuprofen = new AvailablePrescriptions();
                ibuprofen.setPrescriptionName("Ibuprofen");
                ibuprofen.setDescription("Pain Relief");
                availablePrescriptionsRepository.save(ibuprofen);

                AvailablePrescriptions ciprofloxacin = new AvailablePrescriptions();
                ciprofloxacin.setPrescriptionName("Ciprofloxacin");
                ciprofloxacin.setDescription("Antibiotic");
                availablePrescriptionsRepository.save(ciprofloxacin);

            }
        };
    }

}
