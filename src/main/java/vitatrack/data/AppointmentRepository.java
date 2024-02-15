package vitatrack.data;

import org.springframework.data.jpa.repository.JpaRepository;
import vitatrack.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
