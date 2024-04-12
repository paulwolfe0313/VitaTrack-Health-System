package vitatrack.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vitatrack.Bill;
import vitatrack.Patient;
import vitatrack.PatientChart;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    Bill findBillById(Long id);

    List<Bill> findAllByPatientChart(PatientChart patientChart);

    List<Bill> findAllByPaid(boolean paid);
}
