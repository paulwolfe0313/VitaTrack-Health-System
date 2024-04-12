package vitatrack.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vitatrack.Bill;
import vitatrack.PatientChart;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    Bill findBillById(Long id);

    List<Bill> findAllByPatientChart(PatientChart patientChart);

    List<Bill> findAllByPaid(boolean paid);
}