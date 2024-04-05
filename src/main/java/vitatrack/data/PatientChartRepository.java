package vitatrack.data;

import org.springframework.data.jpa.repository.JpaRepository;
import vitatrack.PatientChart;

public interface PatientChartRepository extends JpaRepository<PatientChart, Long> {

}
