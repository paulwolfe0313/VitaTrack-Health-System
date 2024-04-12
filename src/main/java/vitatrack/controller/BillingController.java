package vitatrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vitatrack.Bill;
import vitatrack.Patient;
import vitatrack.service.BillingService;
import vitatrack.service.UserService;

@Controller
public class BillingController {

    BillingService billingService;

    UserService userService;

    @Autowired
    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @RequestMapping(value = "/view-bills", method = RequestMethod.GET)
    public ResponseEntity<List<Bill>> getPatientBills(@RequestParam(value = "patientChartId") Long patientChartId){
        return new ResponseEntity(billingService.getBill(patientChartId), HttpStatus.OK);
    }

    //@RequestMapping(value = "/create-bill", method = RequestMethod.POST)
    //public ResponseEntity<Bill> generateBill(@RequestParam(value = "patientChartId") Long patientChartId){
      //  return new ResponseEntity(billingService.generateBill(patientChartId), HttpStatus.OK);
    //}

    @RequestMapping(value = "/pay-bill", method = RequestMethod.POST)
    public ResponseEntity<Bill> payBill(@RequestParam(value = "billId") Long billId, @RequestParam(value = "selfPay") boolean selfPay){
        return new ResponseEntity(billingService.payBill(billId, selfPay), HttpStatus.OK);
    }

    @RequestMapping(value = "patient-select-bill", method = RequestMethod.GET)
    public String getPatientsBills(Model model){
        List<Patient> patients = userService.getPatients();

        model.addAttribute("patients", patients);

        return "selectpatientbill";
    }

    @RequestMapping(value = "patient-choice-bill", method = RequestMethod.POST)
    public String selectPatientBills(@RequestParam(value = "patientId") Long patientId, Model model){
        List<Bill> bills = billingService.getUnpaidBills(patientId);

        model.addAttribute("bills", bills);

        return "selectbill";
    }
}