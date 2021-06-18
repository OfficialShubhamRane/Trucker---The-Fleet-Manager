package com.example.trucker_demo.service;

import com.example.trucker_demo.model.VehicleDetails_Model;
import com.example.trucker_demo.model.VehicleReading_Model;
import com.example.trucker_demo.repository.VehicleDetailsRepo;
import com.example.trucker_demo.repository.VehicleReadingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.persistence.Basic;
import java.util.List;

@Service
public class VehicleReadingService {

    @Autowired
    VehicleReadingsRepo vehicleReadingsRepo;

    @Autowired
    VehicleDetailsRepo vehicleDetailsRepo;

    public boolean saveAllVehicleReadings(VehicleReading_Model vehicleReading_model) {

        boolean isSaved = false;
        VehicleDetails_Model vehicleDetails_model = vehicleDetailsRepo.getById( vehicleReading_model.getVin() );

        /** engineRpm > readlineRpm, Priority: HIGH */
        if( vehicleReading_model.getEngineRpm() > vehicleDetails_model.getRedlineRpm()){
            System.out.println(vehicleReading_model.getVin() + " EngineRPM > RedLineRPM, Priority: HIGH");
        }

        /** fuelVolume < 10% of maxFuelVolume, Priority: MEDIUM */
        if(vehicleReading_model.getFuelVolume() < vehicleDetails_model.getMaxFuelVolume()/10.0){
            System.out.println( vehicleReading_model.getVin() + ": fuelVolume < 10% of maxFuelVolume, Priority: MEDIUM ");
        }

        /** tire pressure of any tire < 32psi || > 36psi , Priority: LOW */
        if( 32>vehicleReading_model.getTires().getFrontLeft()||vehicleReading_model.getTires().getFrontLeft()>36||
                32>vehicleReading_model.getTires().getFrontRight()||vehicleReading_model.getTires().getFrontRight()>36||
                32>vehicleReading_model.getTires().getRearLeft()||vehicleReading_model.getTires().getRearLeft()>36||
                32>vehicleReading_model.getTires().getRearRight()||vehicleReading_model.getTires().getRearRight()>36){

            System.out.println(vehicleReading_model.getVin() + " Tire pressure LOW, Priority: LOW");

        }

        /** engineCoolantLow = true || checkEngineLightOn = true, Priority: LOW */
        if( vehicleReading_model.isEngineCoolantLow()|| vehicleReading_model.isCheckEngineLightOn() ){
            System.out.println(vehicleReading_model.getVin() + ": Either Engine coolant is LOW OR Engine lights are ON, Priority: LOW");
        }

//        System.out.println( vehicleReading_model );
        vehicleReadingsRepo.save(vehicleReading_model);
        isSaved = true;
        return isSaved;
    }

    public List<VehicleReading_Model> getAllVehicleReadings() {

        return vehicleReadingsRepo.findAll();
    }
}
