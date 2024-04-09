package vitatrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vitatrack.AvailablePrescriptions;
import vitatrack.AvailableProcedures;
import vitatrack.PatientChart;
import vitatrack.service.ChartingService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class ChartingController {

    ChartingService chartingService;

    @Autowired
    public ChartingController(ChartingService chartingService) {
        this.chartingService = chartingService;
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
    public ResponseEntity<HashMap<String, ArrayList<Object>>> getProceduresAndPrescriptions(){
        HashMap<String, ArrayList<Object>> map = chartingService.getProceduresAndPrescriptions();

        return new ResponseEntity(map, HttpStatus.OK);
    }

    @RequestMapping(value = "patient-records", method = RequestMethod.POST)
    public ResponseEntity<List<PatientChart>> getPatientRecords(@RequestParam(value = "patientId") Long patientId){
        return new ResponseEntity(chartingService.getPatientRecords(patientId), HttpStatus.OK);
    }
}
