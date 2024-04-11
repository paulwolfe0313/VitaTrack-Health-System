package vitatrack;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import vitatrack.data.AvailablePrescriptionsRepository;
import vitatrack.data.AvailableProceduresRepository;

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
                                        AvailablePrescriptionsRepository availablePrescriptionsRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
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
