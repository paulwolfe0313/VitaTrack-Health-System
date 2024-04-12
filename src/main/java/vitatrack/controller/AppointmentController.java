package vitatrack.controller;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vitatrack.Appointment;
import vitatrack.Patient;
import vitatrack.Provider;
import vitatrack.service.AppointmentService;
import vitatrack.service.UserService;

@Controller
public class AppointmentController {

    AppointmentService appointmentService;

    UserService userService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, UserService userService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
    }

    @RequestMapping(value = "/schedule-appointment", method = RequestMethod.GET)
    public String newAppointment(Model model){
        Appointment appointment = new Appointment();
        List<Provider> providers = userService.getProviders();
        List<Patient> patients = userService.getPatients();

        model.addAttribute("appointment", appointment);
        model.addAttribute("providers", providers);
        model.addAttribute("patients", patients);

        return "bookappointment";
    }

    @RequestMapping(value = "/book-appointment", method = RequestMethod.POST)
    public String bookAppointment(@ModelAttribute("appointment") Appointment appointment,
                                  @RequestParam("providerId") Long providerId, @RequestParam("patientId") Long patientId){
        Appointment returnedAppointment = appointmentService.newAppointment(appointment, providerId, patientId);

        if (returnedAppointment == null){
            return "bookappointment";
        }

        return "index";
    }

    @RequestMapping(value = "/reschedule-appointment", method = RequestMethod.POST)
    public ResponseEntity<Appointment> rescheduleAppointment(@RequestParam(value = "appointmentId") Long appointmentId,
                                                             @RequestParam(value = "providerId") Long providerId,
                                                             @RequestParam(value = "patientId") Long patientId,
                                                             @RequestParam(value = "appointmentDate") Date appointmentDate,
                                                             @RequestParam(value = "startTime")LocalTime startTime,
                                                             @RequestParam(value = "endTime") LocalTime endTime){
        Appointment returnedAppointment = appointmentService.rescheduleAppointment(appointmentId, providerId, patientId, appointmentDate, startTime, endTime);

        return new ResponseEntity(returnedAppointment, HttpStatus.OK);
    }

    @RequestMapping(value = "/book-appointment/list-providers", method = RequestMethod.GET)
    public ResponseEntity<List<Provider>> getProviders(){
        return new ResponseEntity(userService.getProviders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/book-appointment/check-available", method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkAvailability(@RequestParam(value = "providerId") Long providerId,
                                                     @RequestParam(value = "appointmentDate") Date appointmentDate,
                                                     @RequestParam(value = "startTime")LocalTime startTime,
                                                     @RequestParam(value = "endTime") LocalTime endTime){
        return new ResponseEntity(appointmentService.isSlotAvailable(providerId, appointmentDate, startTime, endTime), HttpStatus.OK);
    }

    @RequestMapping(value = "/view-appointments/provider", method = RequestMethod.GET)
    public ResponseEntity<List<Appointment>> getAppointmentsProvider(@RequestParam(value = "providerId") Long providerId){
        return new ResponseEntity(appointmentService.getProviderAppointments(providerId), HttpStatus.OK);
    }

    @RequestMapping(value = "/view-appointments/patient", method = RequestMethod.GET)
    public ResponseEntity<List<Appointment>> getAppointmentsPatient(@RequestParam(value = "patientId") Long patientId){
        return new ResponseEntity(appointmentService.getPatientAppointments(patientId), HttpStatus.OK);
    }
}