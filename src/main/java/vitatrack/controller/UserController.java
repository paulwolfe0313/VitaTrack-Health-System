package vitatrack.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import vitatrack.Admin;
import vitatrack.Patient;
import vitatrack.Provider;
import vitatrack.service.UserService;

@Controller
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String addPatient(Model model){
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "registration";
    }
    @RequestMapping(value = "/create-patient", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, Patient>> createPatient(Patient p){
        return new ResponseEntity(userService.newPatient(p), HttpStatus.OK);
    }

    @RequestMapping(value = "/create-provider", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, Provider>> createProvider(@RequestParam(value = "firstName") String firstName,
                                                                   @RequestParam(value = "lastName") String lastName,
                                                                   @RequestParam(value = "userName") String userName,
                                                                   @RequestParam(value = "passWord") String passWord,
                                                                   @RequestParam(value = "medicalLicenseNumber") String medicalLicenseNumber){
        return new ResponseEntity(userService.createProvider(firstName, lastName, userName, passWord, medicalLicenseNumber), HttpStatus.OK);
    }

    @RequestMapping(value = "/create-admin", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, Admin>> createAdmin(@RequestParam(value = "firstName") String firstName,
                                                                 @RequestParam(value = "lastName") String lastName,
                                                                 @RequestParam(value = "userName") String userName,
                                                                 @RequestParam(value = "passWord") String passWord){
        return new ResponseEntity(userService.createAdmin(firstName, lastName, userName, passWord), HttpStatus.OK);
    }

    @RequestMapping(value = "login-patient", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Patient>> loginPatient(@RequestParam(value = "userName") String userName, @RequestParam(value = "passWord") String passWord){
        return new ResponseEntity(userService.patientLogIn(userName, passWord), HttpStatus.OK);
    }

    @RequestMapping(value = "login-provider", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Provider>> loginProvider(@RequestParam(value = "userName") String userName, @RequestParam(value = "passWord") String passWord){
        return new ResponseEntity(userService.providerLogIn(userName, passWord), HttpStatus.OK);
    }

    @RequestMapping(value = "login-admin", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Admin>> loginAdmin(@RequestParam(value = "userName") String userName, @RequestParam(value = "passWord") String passWord){
        return new ResponseEntity(userService.adminLogIn(userName, passWord), HttpStatus.OK);
    }
}
