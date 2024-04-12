package vitatrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vitatrack.Appointment;
import vitatrack.Patient;
import vitatrack.Provider;
import vitatrack.data.AppointmentRepository;
import vitatrack.data.PatientRepository;
import vitatrack.data.ProviderRepository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {

    ProviderRepository providerRepository;
    PatientRepository patientRepository;
    AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(ProviderRepository providerRepository, PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
        this.providerRepository = providerRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment newAppointment(Appointment appointment, Long providerId, Long patientId){

        if (!isSlotAvailable(providerId, appointment.getAppointmentDate(), appointment.getStartTime(), appointment.getEndTime())){
            return null;
        }
        Provider bookedProv = providerRepository.findProviderById(providerId);
        Patient bookedPat = patientRepository.findPatientById(patientId);

        bookedProv.getAppointment().add(appointment);
        bookedPat.getAppointment().add(appointment);

        appointment.setProvider(bookedProv);
        appointment.setPatient(bookedPat);

        patientRepository.save(bookedPat);
        providerRepository.save(bookedProv);
        appointmentRepository.save(appointment);

        return appointment;
    }

    public Appointment createAppointment(Long providerId, Long patientId, Date appointmentDate, LocalTime startTime, LocalTime endTime){

        Patient patientToBook = patientRepository.findPatientById(patientId);

        Provider providerToBook = providerRepository.findProviderById(providerId);

        Appointment newAppointment = new Appointment();

        newAppointment.setPatient(patientToBook);
        newAppointment.setProvider(providerToBook);
        newAppointment.setAppointmentDate(appointmentDate);
        newAppointment.setStartTime(startTime);
        newAppointment.setEndTime(endTime);

        patientToBook.getAppointment().add(newAppointment);
        providerToBook.getAppointment().add(newAppointment);

        appointmentRepository.save(newAppointment);
        patientRepository.save(patientToBook);
        providerRepository.save(providerToBook);

        return newAppointment;
    }

    public Appointment rescheduleAppointment(Long appointmentId, Long providerId, Long patientId, Date date, LocalTime startTime, LocalTime endTime){
        if (isSlotAvailable(providerId, date, startTime, endTime)){
            cancelAppointment(appointmentId);
            return createAppointment(providerId, patientId, date, startTime, endTime);
        }
        return null;
    }

    public void cancelAppointment(Long appointmentId){
        appointmentRepository.deleteById(appointmentId);
    }

    public boolean isSlotAvailable(Long providerId, Date date, LocalTime startTime, LocalTime endTime){
        Provider providerToCheck = providerRepository.findProviderById(providerId);

        List<Appointment> appointmentList = appointmentRepository.findAllByProviderAndAppointmentDate(providerToCheck, date);

        for (Appointment app: appointmentList){
            if (startTime.isAfter(app.getStartTime()) && startTime.isBefore(app.getEndTime())) {
                return false;
            } else if (endTime.isAfter(app.getStartTime()) && endTime.isBefore(app.getEndTime())) {
                return false;
            } else if (startTime.isBefore(app.getStartTime()) && endTime.isAfter(app.getStartTime())){
                return false;
            }
        }

        return true;
    }

    public List<Appointment> getProviderAppointments(Long providerId){
        Provider provider = providerRepository.findProviderById(providerId);

        return appointmentRepository.findAllByProvider(provider);
    }

    public List<Appointment> getPatientAppointments(Long patientId){
        Patient patient = patientRepository.findPatientById(patientId);

        return appointmentRepository.findAllByPatient(patient);
    }

    public Appointment getAppointment(Long appointmentId){
        return appointmentRepository.findAppointmentById(appointmentId);
    }

}
