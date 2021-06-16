package com.example.trucker_demo.service;

import com.example.trucker_demo.model.VehicleDetails_Model;
import com.example.trucker_demo.model.VehicleReading_Model;
import com.example.trucker_demo.repository.VehicleDetailsRepo;
import com.example.trucker_demo.repository.VehicleReadingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.persistence.Basic;

@Service
public class VehicleReadingService {

    @Autowired
    VehicleReadingsRepo vehicleReadingsRepo;

    @Autowired
    VehicleDetailsRepo vehicleDetailsRepo;

    VehicleDetails_Model vehicleDetails_model;

    public void saveAllVehicleReadings(VehicleReading_Model vehicleReading_model) {

        String currVehicleVIN = vehicleReading_model.getVin();
        vehicleDetails_model = vehicleDetailsRepo.getById(currVehicleVIN);

        /** engineRpm > readlineRpm, Priority: HIGH */
        if( vehicleReading_model.getEngineRPM() > vehicleDetails_model.getRedlineRPM()){
            System.out.println(vehicleReading_model.getVin() + " EngineRPM > RedLineRPM: High Priority");
        }

        /** fuelVolume < 10% of maxFuelVolume, Priority: MEDIUM */
        if(vehicleReading_model.getFuelVolumn() < vehicleDetails_model.getMaxFuelVolumn()/10.0){
            System.out.println( vehicleReading_model.getVin() + ": fuelVolume < 10% of maxFuelVolume, Priority: MEDIUM ");
        }

        /** tire pressure of any tire < 32psi || > 36psi , Priority: LOW */
        if( 32>vehicleReading_model.getTires().getFrontLeft()||vehicleReading_model.getTires().getFrontLeft()>36||
                32>vehicleReading_model.getTires().getFrontRight()||vehicleReading_model.getTires().getFrontRight()>36||
                32>vehicleReading_model.getTires().getRearLeft()||vehicleReading_model.getTires().getRearLeft()>36||
                32>vehicleReading_model.getTires().getRearRight()||vehicleReading_model.getTires().getRearRight()>36){

            System.out.println(vehicleReading_model.getVin() + " Tire pressure LOW : Low Priority");

        }

        /** engineCoolantLow = true || checkEngineLightOn = true, Priority: LOW */
        if( vehicleReading_model.isEngineCoolantLow() || vehicleReading_model.isCheckEnginLightsOn()){
            System.out.println(vehicleReading_model.getVin() + ": Either Engine coolant is LOW OR Engine lights are ON: Low Priority");
        }

        vehicleReadingsRepo.save(vehicleReading_model);
    }
}
