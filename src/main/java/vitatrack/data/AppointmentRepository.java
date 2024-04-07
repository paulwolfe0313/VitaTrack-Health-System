package vitatrack.data;

import org.springframework.data.jpa.repository.JpaRepository;
import vitatrack.Appointment;
import vitatrack.Patient;
import vitatrack.Provider;

import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findAppointmentById(Long id);

    List<Appointment> findAllByProviderAndAppointmentDate(Provider provider, Date appointmentDate);

    List<Appointment> findAllByProvider(Provider provider);

    List<Appointment> findAllByPatient(Patient patient);
}
