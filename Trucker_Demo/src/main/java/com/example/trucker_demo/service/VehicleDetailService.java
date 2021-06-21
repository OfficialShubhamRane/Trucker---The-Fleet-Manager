package com.example.trucker_demo.service;

import com.example.trucker_demo.model.VehicleDetails_Model;
import com.example.trucker_demo.repository.VehicleDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleDetailService {

    @Autowired
    VehicleDetailsRepo vehicleDetailsRepo;

    /** Save all vehicles all details */
    public void saveAllVehicleDetails(VehicleDetails_Model[] vehicleDetailsArray){

        for (VehicleDetails_Model vehicleDetails : vehicleDetailsArray) {
//            System.out.println( vehicleDetails );
            vehicleDetailsRepo.save(vehicleDetails);
        }
    }

    /** REST api for returning all vehicles all details */
    public List<VehicleDetails_Model> getAllVehicleDetails() {
        return vehicleDetailsRepo.findAll();
    }

    /** Rest api for returning specific vehicle details */
    public VehicleDetails_Model getVehicleDetails(String vin) {

        return vehicleDetailsRepo.findAllByVin(vin);
    }
}
