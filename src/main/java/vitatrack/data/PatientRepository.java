package vitatrack.data;

import org.springframework.stereotype.Repository;
import vitatrack.Patient;

@Repository
public interface PatientRepository extends UserRepository<Patient> {
}
