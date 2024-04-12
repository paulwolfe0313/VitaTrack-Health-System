package vitatrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vitatrack.*;
import vitatrack.service.AppointmentService;
import vitatrack.service.ChartingService;
import vitatrack.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class ChartingController {

    ChartingService chartingService;

    UserService userService;

    AppointmentService appointmentService;

    @Autowired
    public ChartingController(ChartingService chartingService, UserService userService, AppointmentService appointmentService) {
        this.chartingService = chartingService;
        this.userService = userService;
        this.appointmentService = appointmentService;
    }

    @RequestMapping(value = "/chart", method = RequestMethod.POST)
    public ResponseEntity<PatientChart> createChart(@RequestParam(value = "patientId") Long patientId,
                                                    @RequestParam(value = "providerId") Long providerId,
                                                    @RequestParam(value = "appointmentId") Long appointmentId,
                                                    @RequestParam(value = "height") Integer height,
                                                    @RequestParam(value = "weight") Integer weight,
                                                    @RequestParam(value = "systolicBloodPressure") Integer systolicBloodPressure,
                                                    @RequestParam(value = "diastolicBloodPressure") Integer diastolicBloodPressure,
                                                    @RequestParam(value = "restingHeartRate") Integer restingHeartRate,
                                                    @RequestParam(value = "procedures") List<AvailableProcedures> procedures,
                                                    @RequestParam(value = "prescriptions") List<AvailablePrescriptions> prescriptions){
        PatientChart chart = chartingService.submitChart(patientId, providerId, appointmentId, height, weight,
                                                        systolicBloodPressure, diastolicBloodPressure, restingHeartRate,
                                                        procedures, prescriptions);
        return new ResponseEntity(chart, HttpStatus.OK);
    }

    // Returns Hash Map with string key "Procedures" or "Prescriptions"
    @RequestMapping(value = "/chart", method = RequestMethod.GET)
    public String getChartInfo(@RequestParam(value = "appointmentId") Long appointmentId, Model model){

        Appointment appointment = appointmentService.getAppointment(appointmentId);

        List<AvailableProcedures> procedures = chartingService.getProcedures();
        List<AvailablePrescriptions> prescriptions = chartingService.getPrescriptions();

        PatientChart chart = new PatientChart();

        model.addAttribute("appointment", appointment);
        model.addAttribute("procedures", procedures);
        model.addAttribute("prescriptions", prescriptions);
        model.addAttribute("chart", chart);



        return "";
    }

    @RequestMapping(value = "patient-records", method = RequestMethod.POST)
    public ResponseEntity<List<PatientChart>> getPatientRecords(@RequestParam(value = "patientId") Long patientId){
        return new ResponseEntity(chartingService.getPatientRecords(patientId), HttpStatus.OK);
    }

    @RequestMapping(value = "patient-select", method = RequestMethod.GET)
    public String getPatients(Model model){
        List<Patient> patients = userService.getPatients();

        model.addAttribute("patients", patients);

        return "selectpatientappointments";
    }

    @RequestMapping(value = "patient-choice", method = RequestMethod.POST)
    public String selectPatient(@RequestParam(value = "patientId") Long patientId, Model model){
        List<Appointment> appointments = appointmentService.getPatientAppointments(patientId);

        model.addAttribute("appointments", appointments);

        return "selectappointment";
    }
}
