package vitatrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vitatrack.Appointment;
import vitatrack.Patient;
import vitatrack.PatientChart;
import vitatrack.Provider;
import vitatrack.data.AppointmentRepository;
import vitatrack.data.PatientChartRepository;
import vitatrack.data.PatientRepository;
import vitatrack.data.ProviderRepository;

@Service
public class ChartingService {

    PatientRepository patientRepository;
    ProviderRepository providerRepository;
    AppointmentRepository appointmentRepository;
    PatientChartRepository patientChartRepository;

    @Autowired
    public ChartingService(PatientRepository patientRepository, ProviderRepository providerRepository, AppointmentRepository appointmentRepository, PatientChartRepository patientChartRepository) {
        this.patientRepository = patientRepository;
        this.providerRepository = providerRepository;
        this.appointmentRepository = appointmentRepository;
        this.patientChartRepository = patientChartRepository;
    }

    public PatientChart createNewChart(Long patientId, Long providerId, Long appointmentId) {
        Patient patient = patientRepository.findPatientById(patientId);
        Provider provider = providerRepository.findProviderById(providerId);
        Appointment appointment = appointmentRepository.findAppointmentById(appointmentId);

        PatientChart chart = new PatientChart();

        chart.setPatient(patient);
        chart.setProvider(provider);
        chart.setAppointment(appointment);

        patient.getPatientChart().add(chart);
        provider.getPatientChart().add(chart);
        appointment.setPatientChart(chart);

        patientChartRepository.save(chart);
        patientRepository.save(patient);
        providerRepository.save(provider);
        appointmentRepository.save(appointment);

        return chart;
    }

    public void setVitals(Long patientChartId, Integer height, Integer weight, Integer bloodPressureSystolic, Integer bloodPressureDiastolic, Integer restingHeartRate){
        PatientChart chart = patientChartRepository.findPatientChartById(patientChartId);

        if (height != null){
            chart.setHeight(height);
        }
        if (weight != null){
            chart.setWeight(weight);
        }
        if (bloodPressureSystolic != null){
            chart.setBloodPressureSystolic(bloodPressureSystolic);
        }
        if (bloodPressureDiastolic != null){
            chart.setBloodPressureDiastolic(bloodPressureDiastolic);
        }
        if (restingHeartRate != null){
            chart.setRestingHeartRate(restingHeartRate);
        }
    }
}
