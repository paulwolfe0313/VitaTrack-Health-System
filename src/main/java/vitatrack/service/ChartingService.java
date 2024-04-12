package vitatrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vitatrack.*;
import vitatrack.data.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartingService {

    PatientRepository patientRepository;
    ProviderRepository providerRepository;
    AppointmentRepository appointmentRepository;
    PatientChartRepository patientChartRepository;
    AvailableProceduresRepository availableProceduresRepository;
    AvailablePrescriptionsRepository availablePrescriptionsRepository;

    @Autowired
    public ChartingService(PatientRepository patientRepository, ProviderRepository providerRepository, AppointmentRepository appointmentRepository,
                           PatientChartRepository patientChartRepository, AvailableProceduresRepository availableProceduresRepository,
                           AvailablePrescriptionsRepository availablePrescriptionsRepository) {
        this.patientRepository = patientRepository;
        this.providerRepository = providerRepository;
        this.appointmentRepository = appointmentRepository;
        this.patientChartRepository = patientChartRepository;
        this.availableProceduresRepository = availableProceduresRepository;
        this.availablePrescriptionsRepository = availablePrescriptionsRepository;
    }

    public PatientChart submitChart(Long patientId, Long providerId, Long appointmentId,
                                    Integer height, Integer weight, Integer bloodPressureSystolic,
                                    Integer bloodPressureDiastolic, Integer restingHeartRate,
                                    List<AvailableProcedures> procedures, List<AvailablePrescriptions> prescriptions){
        PatientChart chart = createNewChart(patientId, providerId, appointmentId);
        return setVitals(chart.getId(), height, weight, bloodPressureSystolic, bloodPressureDiastolic, restingHeartRate, procedures, prescriptions);
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

    public PatientChart setVitals(Long patientChartId, Integer height, Integer weight, Integer bloodPressureSystolic,
                                  Integer bloodPressureDiastolic, Integer restingHeartRate,
                                  List<AvailableProcedures> procedures, List<AvailablePrescriptions> prescriptions){
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
        if (!procedures.isEmpty()){
            chart.getProcedures().addAll(procedures);
        }
        if (!prescriptions.isEmpty()){
            chart.getPrescriptions().addAll(prescriptions);
        }

        patientChartRepository.save(chart);
        return chart;
    }

    public List<PatientChart> getPatientRecords(Long patientId){
        Patient patient = patientRepository.findPatientById(patientId);
        return patientChartRepository.findAllByPatient(patient);
    }

    public HashMap<String, ArrayList<Object>> getProceduresAndPrescriptions(){
        HashMap<String, ArrayList<Object>> map = new HashMap<>();
        map.put("Procedures", new ArrayList<>(availableProceduresRepository.findAll()));
        map.put("Prescriptions", new ArrayList<>(availablePrescriptionsRepository.findAll()));
        return map;
    }
}
