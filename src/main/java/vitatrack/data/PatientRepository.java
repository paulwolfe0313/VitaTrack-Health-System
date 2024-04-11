package vitatrack.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vitatrack.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findPatientById(Long id);
    Patient findPatientByUserName(String userName);
    Patient findPatientByUserNameAndPassWord(String userName, String passWord);

    Patient findPatientByFirstNameAndLastName(String firstName, String lastName);
}
