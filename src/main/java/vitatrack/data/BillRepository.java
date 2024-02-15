package vitatrack.data;

import org.springframework.data.jpa.repository.JpaRepository;
import vitatrack.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
