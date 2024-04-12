package vitatrack.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vitatrack.AvailablePrescriptions;

import java.util.List;

@Repository
public interface AvailablePrescriptionsRepository extends JpaRepository<AvailablePrescriptions, Long> {

}
