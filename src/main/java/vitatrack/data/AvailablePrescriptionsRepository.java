package vitatrack.data;

import org.springframework.data.jpa.repository.JpaRepository;
import vitatrack.AvailablePrescriptions;

public interface AvailablePrescriptionsRepository extends JpaRepository<AvailablePrescriptions, Long> {
}
