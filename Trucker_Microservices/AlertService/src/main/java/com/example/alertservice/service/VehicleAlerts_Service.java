package com.example.alertservice.service;

import com.example.alertservice.model.VehicleAlerts_Model;
import com.example.alertservice.model.VehicleDetails_Model;
import com.example.alertservice.model.VehicleReading_Model;
import com.example.alertservice.repository.VehicleAlerts_Repository;
import com.example.alertservice.repository.VehicleDetails_Repository;
import com.example.alertservice.repository.VehicleReadings_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleAlerts_Service {

    @Autowired
    VehicleDetails_Repository vehicleDetails_repository;

    @Autowired
    VehicleReadings_Repository vehicleReadings_repository;

    @Autowired
    VehicleAlerts_Repository vehicleAlerts_repository;

    @Autowired
    JavaMailSender javaMailSender;

    /** Checks if Vin is valid and does necessary */
    public List<VehicleAlerts_Model> getVehicleAlerts(String vin) {

        List<VehicleAlerts_Model> vehicleAlerts_List = new ArrayList<>();

        if(vehicleReadings_repository.existsByVin(vin)){
            vehicleAlerts_List =  checkForAlerts(vin);
        }else{
            System.out.println("Wrong vin");
        }
        return vehicleAlerts_List;
    }

    /** Condition based alert checking */
    private List<VehicleAlerts_Model> checkForAlerts(String vin) {

        VehicleDetails_Model vehicleDetails_model = vehicleDetails_repository.findByVin(vin);
        List<VehicleReading_Model> vehicleReading_List = vehicleReadings_repository.findAllByVin(vin);
        List<VehicleAlerts_Model> vehicleAlerts_List = new ArrayList<>();
        String alertRule = null;
        String alertLevel = null;

        for(VehicleReading_Model vehicleReading_model : vehicleReading_List ){

            /** engineRpm > readlineRpm, Priority: HIGH */
            if( vehicleReading_model.getEngineRpm() > vehicleDetails_model.getRedlineRpm()){
                alertRule = "Rule 1";
                alertLevel = "HIGH";

                VehicleAlerts_Model vehicleAlerts_model = new VehicleAlerts_Model();
                vehicleAlerts_model.setVin(vehicleReading_model.getVin());
                vehicleAlerts_model.setTimestamp(vehicleReading_model.getTimestamp());
                vehicleAlerts_model.setRule(alertRule);
                vehicleAlerts_model.setAlertLevel(alertLevel);
                vehicleAlerts_List.add(vehicleAlerts_model);

                vehicleAlerts_repository.save(vehicleAlerts_model);

                String body = "Vehicle "+vin+" is crossing the Red line RPM of " + vehicleDetails_model.getRedlineRpm()
                        + " to " + vehicleReading_model.getEngineRpm();
                String subject = "HIGH priority alert on vehicle: "+vin;
                sendEmail(body ,subject );
//                System.out.println(vehicleReading_model.getVin() + " EngineRPM > RedLineRPM, Priority: HIGH");

            }

            /** fuelVolume < 10% of maxFuelVolume, Priority: MEDIUM */
            if(vehicleReading_model.getFuelVolume() < vehicleDetails_model.getMaxFuelVolume()/10.0){
                alertRule = "Rule 2";
                alertLevel = "MEDIUM";

                VehicleAlerts_Model vehicleAlerts_model = new VehicleAlerts_Model();
                vehicleAlerts_model.setVin(vehicleReading_model.getVin());
                vehicleAlerts_model.setTimestamp(vehicleReading_model.getTimestamp());
                vehicleAlerts_model.setRule(alertRule);
                vehicleAlerts_model.setAlertLevel(alertLevel);
                vehicleAlerts_List.add(vehicleAlerts_model);

                vehicleAlerts_repository.save(vehicleAlerts_model);
//                System.out.println( vehicleReading_model.getVin() + ": fuelVolume < 10% of maxFuelVolume, Priority: MEDIUM ");

            }

            /** tire pressure of any tire < 32psi || > 36psi , Priority: LOW */
            if( 32>vehicleReading_model.getTires().getFrontLeft()||vehicleReading_model.getTires().getFrontLeft()>36||
                    32>vehicleReading_model.getTires().getFrontRight()||vehicleReading_model.getTires().getFrontRight()>36||
                    32>vehicleReading_model.getTires().getRearLeft()||vehicleReading_model.getTires().getRearLeft()>36||
                    32>vehicleReading_model.getTires().getRearRight()||vehicleReading_model.getTires().getRearRight()>36){
                alertRule = "Rule 3";
                alertLevel = "LOW";

                VehicleAlerts_Model vehicleAlerts_model = new VehicleAlerts_Model();
                vehicleAlerts_model.setVin(vehicleReading_model.getVin());
                vehicleAlerts_model.setTimestamp(vehicleReading_model.getTimestamp());
                vehicleAlerts_model.setRule(alertRule);
                vehicleAlerts_model.setAlertLevel(alertLevel);
                vehicleAlerts_List.add(vehicleAlerts_model);

                vehicleAlerts_repository.save(vehicleAlerts_model);
//            System.out.println(vehicleReading_model.getVin() + " Tire pressure LOW, Priority: LOW");

            }

            /** engineCoolantLow = true || checkEngineLightOn = true, Priority: LOW */
            if( vehicleReading_model.isEngineCoolantLow()|| vehicleReading_model.isCheckEngineLightOn() ){
                alertRule = "Rule 4";
                alertLevel = "LOW";

                VehicleAlerts_Model vehicleAlerts_model = new VehicleAlerts_Model();
                vehicleAlerts_model.setVin(vehicleReading_model.getVin());
                vehicleAlerts_model.setTimestamp(vehicleReading_model.getTimestamp());
                vehicleAlerts_model.setRule(alertRule);
                vehicleAlerts_model.setAlertLevel(alertLevel);
                vehicleAlerts_List.add(vehicleAlerts_model);

                vehicleAlerts_repository.save(vehicleAlerts_model);
//            System.out.println(vehicleReading_model.getVin() + ": Either Engine coolant is LOW OR Engine lights are ON, Priority: LOW");

            }

        }
        return vehicleAlerts_List;

    }

    /** Sends email */
    private void sendEmail(String body, String subject){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("from.springboot.gmail.com");
        message.setTo("shubham16.ranez@gmail.com");
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);

    }

}
