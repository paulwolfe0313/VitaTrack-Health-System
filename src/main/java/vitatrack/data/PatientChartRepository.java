package vitatrack.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vitatrack.Patient;
import vitatrack.PatientChart;

import java.util.List;

@Repository
public interface PatientChartRepository extends JpaRepository<PatientChart, Long> {

    PatientChart findPatientChartById(Long id);

    List<PatientChart> findAllByPatient(Patient patient);

}
