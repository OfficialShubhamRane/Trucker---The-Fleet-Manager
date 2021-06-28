package com.example.readingservice.service;

import com.example.readingservice.model.VehicleReading_Model;
import com.example.readingservice.repository.VehicleReadings_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleReading_Service {

    @Autowired
    VehicleReadings_Repository vehicleReadings_repository;

    /** saves all vehicle readings */
    public void saveVehicleReadings(VehicleReading_Model vehicleReading_model) {
        vehicleReadings_repository.save(vehicleReading_model);
    }

    /** returns vehicle readings */
    public List<VehicleReading_Model> getVehicleReadings(String vin) {

        List<VehicleReading_Model> vehicleReading_list = new ArrayList<>();

        if(vin == null){
            vehicleReading_list = vehicleReadings_repository.findAll();
        }else if(vehicleReadings_repository.existsByVin(vin)){
            vehicleReading_list = vehicleReadings_repository.findAllByVin(vin);
        }else{
            System.out.println("No readings for this vin");
        }

        return  vehicleReading_list;
    }

}
