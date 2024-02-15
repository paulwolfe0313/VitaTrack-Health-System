package vitatrack.data;

import org.springframework.data.jpa.repository.JpaRepository;
import vitatrack.AvailableProcedures;

public interface AvailableProceduresRepository extends JpaRepository<AvailableProcedures, Long> {
}
