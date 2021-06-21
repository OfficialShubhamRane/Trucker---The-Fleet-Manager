package com.example.trucker_demo.service;

import com.example.trucker_demo.model.Alerts_Model;
import com.example.trucker_demo.model.VehicleDetails_Model;
import com.example.trucker_demo.model.VehicleReading_Model;
import com.example.trucker_demo.repository.AlertsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class AlertsService  {

    @Autowired
    AlertsRepo alertsRepo;

    @Autowired
    MailSenderService mailSenderService;

    /** Check for alerts comparing details and reading values */
    public void checkForAlerts(VehicleDetails_Model vehicleDetails_model, VehicleReading_Model vehicleReading_model) {

        String alertRule = null;
        String alertLevel = null;
        String vin = vehicleReading_model.getVin();

        /** engineRpm > readlineRpm, Priority: HIGH */
        if( vehicleReading_model.getEngineRpm() > vehicleDetails_model.getRedlineRpm()){
            alertRule = "Rule 1";
            alertLevel = "HIGH";
            saveAlerts(vin, alertRule, alertLevel);

            String body = "Vehicle "+vin+" is crossing the Red line RPM of " + vehicleDetails_model.getRedlineRpm()
                    + " to " + vehicleReading_model.getEngineRpm();
            String subject = "HIGH priority alert on vehicle: "+vin;

            mailSenderService.sendEmail(body ,subject );
            System.out.println(vehicleReading_model.getVin() + " EngineRPM > RedLineRPM, Priority: HIGH");

        }

        /** fuelVolume < 10% of maxFuelVolume, Priority: MEDIUM */
        if(vehicleReading_model.getFuelVolume() < vehicleDetails_model.getMaxFuelVolume()/10.0){
            alertRule = "Rule 2";
            alertLevel = "MEDIUM";
            saveAlerts(vin, alertRule, alertLevel);
            System.out.println( vehicleReading_model.getVin() + ": fuelVolume < 10% of maxFuelVolume, Priority: MEDIUM ");

        }

        /** tire pressure of any tire < 32psi || > 36psi , Priority: LOW */
        if( 32>vehicleReading_model.getTires().getFrontLeft()||vehicleReading_model.getTires().getFrontLeft()>36||
                32>vehicleReading_model.getTires().getFrontRight()||vehicleReading_model.getTires().getFrontRight()>36||
                32>vehicleReading_model.getTires().getRearLeft()||vehicleReading_model.getTires().getRearLeft()>36||
                32>vehicleReading_model.getTires().getRearRight()||vehicleReading_model.getTires().getRearRight()>36){
            alertRule = "Rule 3";
            alertLevel = "LOW";
            saveAlerts(vin, alertRule, alertLevel);
            System.out.println(vehicleReading_model.getVin() + " Tire pressure LOW, Priority: LOW");

        }

        /** engineCoolantLow = true || checkEngineLightOn = true, Priority: LOW */
        if( vehicleReading_model.isEngineCoolantLow()|| vehicleReading_model.isCheckEngineLightOn() ){
            alertRule = "Rule 4";
            alertLevel = "LOW";
            saveAlerts(vin, alertRule, alertLevel);
            System.out.println(vehicleReading_model.getVin() + ": Either Engine coolant is LOW OR Engine lights are ON, Priority: LOW");

        }

    }

    /** Saving every generated alert for every vehicle */
    private void saveAlerts(String vin, String alertRule, String alertLevel){
        Alerts_Model alerts_model = new Alerts_Model();
        alerts_model.setVin(vin);
        alerts_model.setRule(alertRule);
        alerts_model.setAlertLevel(alertLevel);
        Date date = new Date();
        alerts_model.setTimestamp(new Timestamp(date.getTime()));
        alertsRepo.save(alerts_model);
    }

    /** Rest api for returning all vehicle alerts */
    public List<Alerts_Model> getAllAlerts() {
        return alertsRepo.findAll();
    }

    /** REST api returning vehicle specific alerts based on VIN as key */
    public List<Alerts_Model> getVehicleAlerts(String vin) {
        return alertsRepo.findAllByVin(vin);
    }

    /** REST API for returning all vehicles HIGH alerts from last 2 hours */
    public List<Alerts_Model> getRecentAlerts() {
        return alertsRepo.findAllByTimestamp();
    }



}
