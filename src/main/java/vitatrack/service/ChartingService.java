package vitatrack.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vitatrack.Appointment;
import vitatrack.AvailablePrescriptions;
import vitatrack.AvailableProcedures;
import vitatrack.Patient;
import vitatrack.PatientChart;
import vitatrack.Provider;
import vitatrack.data.AppointmentRepository;
import vitatrack.data.AvailablePrescriptionsRepository;
import vitatrack.data.AvailableProceduresRepository;
import vitatrack.data.PatientChartRepository;
import vitatrack.data.PatientRepository;
import vitatrack.data.ProviderRepository;

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

    public PatientChart submitChart(Appointment appointment, PatientChart chart, AvailableProcedures procedure, AvailablePrescriptions prescription){
        appointment.setPatientChart(chart);
        chart.setAppointment(appointment);
        chart.getProcedures().add(procedure);
        chart.getPrescriptions().add(prescription);

        Patient pat = appointment.getPatient();
        Provider prov = appointment.getProvider();

        chart.setPatient(pat);
        chart.setProvider(prov);

        pat.getPatientChart().add(chart);
        prov.getPatientChart().add(chart);

        patientRepository.save(pat);
        providerRepository.save(prov);
        appointmentRepository.save(appointment);
        patientChartRepository.save(chart);

        return chart;
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

    public List<AvailableProcedures> getProcedures(){
        return availableProceduresRepository.findAll().stream().toList();
    }

    public List<AvailablePrescriptions> getPrescriptions(){
        return availablePrescriptionsRepository.findAll().stream().toList();
    }

    public AvailableProcedures getProcedureById(Long procedureId){
        return availableProceduresRepository.findAvailableProceduresById(procedureId);
    }

    public AvailablePrescriptions getPrescriptionById(Long prescriptionId){
        return availablePrescriptionsRepository.findAvailablePrescriptionsById(prescriptionId);
    }
}