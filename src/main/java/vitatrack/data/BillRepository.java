package vitatrack.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vitatrack.Bill;
import vitatrack.Patient;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    Bill findBillById(Long id);

    List<Bill> findAllByPatient(Patient patient);
}
