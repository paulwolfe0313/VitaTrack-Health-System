package vitatrack.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vitatrack.AvailableProcedures;

@Repository
public interface AvailableProceduresRepository extends JpaRepository<AvailableProcedures, Long> {
}
