package vitatrack.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vitatrack.AvailablePrescriptions;

@Repository
public interface AvailablePrescriptionsRepository extends JpaRepository<AvailablePrescriptions, Long> {

    AvailablePrescriptions findAvailablePrescriptionsById(Long id);

}