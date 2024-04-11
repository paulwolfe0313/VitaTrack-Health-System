package vitatrack.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vitatrack.Admin;
import vitatrack.Patient;
import vitatrack.Provider;
import vitatrack.service.UserService;

@RestController
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/create-patient", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, Patient>> createPatient(@RequestParam(value = "firstName") String firstName,
                                                                  @RequestParam(value = "lastName") String lastName,
                                                                  @RequestParam(value = "userName") String userName,
                                                                  @RequestParam(value = "passWord") String passWord,
                                                                  @RequestParam(value = "paymentCardNumber") String paymentCardNumber,
                                                                  @RequestParam(value = "ccCVV") String ccCVV,
                                                                  @RequestParam(value = "ccExpiration") String ccExpiration,
                                                                  @RequestParam(value = "insuranceProvider") String insuranceProvider,
                                                                  @RequestParam(value = "insuranceNumber") String insuranceNumber){
        return new ResponseEntity(userService.createPatient(firstName, lastName, userName, passWord, paymentCardNumber, ccCVV, ccExpiration, insuranceProvider, insuranceNumber), HttpStatus.OK);
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
