package vitatrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vitatrack.Bill;
import vitatrack.service.BillingService;

import java.util.List;

@RestController
public class BillingController {

    BillingService billingService;

    @Autowired
    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @RequestMapping(value = "/view-bills", method = RequestMethod.GET)
    public ResponseEntity<List<Bill>> getPatientBills(@RequestParam(value = "patientId") Long patientId){
        return new ResponseEntity(billingService.getPatientBills(patientId), HttpStatus.OK);
    }

    @RequestMapping(value = "/create-bill", method = RequestMethod.POST)
    public ResponseEntity<Bill> generateBill(@RequestParam(value = "patientChartId") Long patientChartId){
        return new ResponseEntity(billingService.generateBill(patientChartId), HttpStatus.OK);
    }

    @RequestMapping(value = "/pay-bill", method = RequestMethod.POST)
    public ResponseEntity<Bill> payBill(@RequestParam(value = "billId") Long billId, @RequestParam(value = "selfPay") boolean selfPay){
        return new ResponseEntity(billingService.payBill(billId, selfPay), HttpStatus.OK);
    }
}
