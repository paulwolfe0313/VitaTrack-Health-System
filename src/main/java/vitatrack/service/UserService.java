package vitatrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vitatrack.Admin;
import vitatrack.Patient;
import vitatrack.Provider;
import vitatrack.data.AdminRepository;
import vitatrack.data.PatientRepository;
import vitatrack.data.ProviderRepository;

import java.util.HashMap;

@Service
public class UserService {

    PatientRepository patientRepository;
    ProviderRepository providerRepository;
    AdminRepository adminRepository;

    @Autowired
    public UserService(PatientRepository patientRepository, ProviderRepository providerRepository, AdminRepository adminRepository) {
        this.patientRepository = patientRepository;
        this.providerRepository = providerRepository;
        this.adminRepository = adminRepository;
    }

    public HashMap<String, Patient> createPatient(String firstName, String lastName, String userName, String passWord, String paymentCardNumber, String ccCVV, String ccExpiration, String insuranceProvider, String insuranceNumber){

        HashMap<String, Patient> response = new HashMap<>();

        // Check if patient already exists
        if (!(patientRepository.findPatientByUserName(userName) == null)){
            response.put("Patient already exists!", null);
            return response;
        }

        // Create and set patient
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setUserName(userName);
        patient.setPassWord(passWord);
        patient.setPaymentCardNumber(paymentCardNumber);
        patient.setCcCVV(ccCVV);
        patient.setCcExpiration(ccExpiration);
        patient.setInsuranceProvider(insuranceProvider);
        patient.setInsuranceNumber(insuranceNumber);

        patientRepository.save(patient);

        response.put("Pateint Created!", patient);

        return response;
    }

    public HashMap<String, Provider> createProvider(String firstName, String lastName, String userName, String passWord, String medicalLicenseNumber){

        HashMap<String, Provider> response = new HashMap<>();

        // Check if provider already exists
        if (!(providerRepository.findProviderByUserName(userName) == null)){
            response.put("Provider already exists!", null);
            return response;
        }

        // Create and set provider
        Provider provider = new Provider();
        provider.setFirstName(firstName);
        provider.setLastName(lastName);
        provider.setUserName(userName);
        provider.setPassWord(passWord);
        provider.setMedicalLicenseNumber(medicalLicenseNumber);

        providerRepository.save(provider);

        response.put("Provider Created!", provider);

        return response;
    }

    public HashMap<String, Admin> createAdmin(String firstName, String lastName, String userName, String passWord){

        HashMap<String, Admin> response = new HashMap<>();

        // Check if admin already exists
        if (!(adminRepository.findAdminByUserName(userName) == null)){
            response.put("Admin already exists!", null);
            return response;
        }

        // Create and set admin
        Admin admin = new Admin();
        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        admin.setUserName(userName);
        admin.setPassWord(passWord);

        adminRepository.save(admin);

        response.put("Admin Created!", admin);
        return response;
    }

    public HashMap<String, Patient> patientLogIn(String userName, String passWord){
        HashMap<String, Patient> response = new HashMap<>();

        // Check if userName exists, then if userName and password match exists
        if (patientRepository.findPatientByUserName(userName) == null){
            response.put("UserName not Found!", null);
            return response;
        } else if (patientRepository.findPatientByUserNameAndPassWord(userName, passWord) == null){
            response.put("Invalid Password!", null);
            return response;
        } else {
            response.put("Logged In Successfully!", patientRepository.findPatientByUserNameAndPassWord(userName, passWord));
            return response;
        }
    }

    public HashMap<String, Provider> providerLogIn(String userName, String passWord){
        HashMap<String, Provider> response = new HashMap<>();

        // Check if userName exists, then if userName and password match exists
        if (providerRepository.findProviderByUserName(userName) == null){
            response.put("UserName not Found!", null);
            return response;
        } else if (providerRepository.findProviderByUserNameAndPassWord(userName, passWord) == null){
            response.put("Invalid Password!", null);
            return response;
        } else {
            response.put("Logged In Successfully!", providerRepository.findProviderByUserNameAndPassWord(userName, passWord));
            return response;
        }
    }

    public HashMap<String, Admin> adminLogIn(String userName, String passWord){
        HashMap<String, Admin> response = new HashMap<>();

        // Check if userName exists, then if userName and password match exists
        if (adminRepository.findAdminByUserName(userName) == null){
            response.put("UserName not Found!", null);
            return response;
        } else if (adminRepository.findAdminByUserNameAndPassWord(userName, passWord) == null){
            response.put("Invalid Password!", null);
            return response;
        } else {
            response.put("Logged In Successfully!", adminRepository.findAdminByUserNameAndPassWord(userName, passWord));
            return response;
        }
    }

}
