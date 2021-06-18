package com.example.trucker_demo.service;

import com.example.trucker_demo.model.Alerts_Model;
import com.example.trucker_demo.model.VehicleDetails_Model;
import com.example.trucker_demo.model.VehicleReading_Model;
import com.example.trucker_demo.repository.AlertsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class AlertsService  {

    @Autowired
    AlertsRepo alertsRepo;

    public void checkForAlerts(VehicleDetails_Model vehicleDetails_model, VehicleReading_Model vehicleReading_model) {

        String alertRule = null;
        String alertLevel = null;
        String vin = vehicleReading_model.getVin();

        /** engineRpm > readlineRpm, Priority: HIGH */
        if( vehicleReading_model.getEngineRpm() > vehicleDetails_model.getRedlineRpm()){
            alertRule = "Rule 1";
            alertLevel = "HIGH";
            saveAlerts(vin, alertRule, alertLevel);
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

    private void saveAlerts(String vin, String alertRule, String alertLevel){
        Alerts_Model alerts_model = new Alerts_Model();
        alerts_model.setVin(vin);
        alerts_model.setRule(alertRule);
        alerts_model.setAlertLevel(alertLevel);
        Date date = new Date();
        alerts_model.setTimestamp(new Timestamp(date.getTime()));
        alertsRepo.save(alerts_model);
    }

}
