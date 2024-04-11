package vitatrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vitatrack.AvailableProcedures;
import vitatrack.Bill;
import vitatrack.Patient;
import vitatrack.PatientChart;
import vitatrack.data.AdminRepository;
import vitatrack.data.BillRepository;
import vitatrack.data.PatientChartRepository;
import vitatrack.data.PatientRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BillingService {

    PatientChartRepository patientChartRepository;
    BillRepository billRepository;
    PatientRepository patientRepository;
    AdminRepository adminRepository;

    @Autowired
    public BillingService(PatientChartRepository patientChartRepository, BillRepository billRepository, PatientRepository patientRepository, AdminRepository adminRepository) {
        this.patientChartRepository = patientChartRepository;
        this.billRepository = billRepository;
        this.patientRepository = patientRepository;
        this.adminRepository = adminRepository;
    }

    public Bill generateBill(Long patientChartId){
        PatientChart chart = patientChartRepository.findPatientChartById(patientChartId);
        Bill bill = new Bill();
        BigDecimal totalCost = BigDecimal.valueOf(0);

        for (AvailableProcedures procedure: chart.getProcedures()){
            totalCost = totalCost.add(procedure.getProcedureCost());
        }

        bill.setTotalCost(totalCost);

        chart.setBill(bill);
        bill.setPatientChart(chart);

        patientChartRepository.save(chart);
        billRepository.save(bill);

        return bill;
    }

    public Bill payBill(Long billId, boolean selfPay){
        Bill bill = billRepository.findBillById(billId);
        PatientChart chart = bill.getPatientChart();
        Patient patient = bill.getPatientChart().getPatient();

        if (selfPay){
            bill.setPaymentCard(patient.getPaymentCardNumber());
        } else {
            bill.setPaymentCard(patient.getInsuranceNumber());
        }
        bill.setPaid(true);

        billRepository.save(bill);

        return bill;
    }

    public List<Bill> getPatientBills(Long patientId){
        Patient patient = patientRepository.findPatientById(patientId);

        return billRepository.findAllByPatient(patient);
    }


}
