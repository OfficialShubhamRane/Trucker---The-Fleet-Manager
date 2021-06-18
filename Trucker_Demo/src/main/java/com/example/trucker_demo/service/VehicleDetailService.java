package com.example.trucker_demo.service;

import com.example.trucker_demo.model.VehicleDetails_Model;
import com.example.trucker_demo.repository.VehicleDetailsRepo;
import com.example.trucker_demo.repository.VehicleReadingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

@Service
public class VehicleDetailService {

    @Autowired
    VehicleDetailsRepo vehicleDetailsRepo;

    public boolean saveAllVehicleDetails(VehicleDetails_Model[] vehicleDetailsArray){

        for (VehicleDetails_Model vehicleDetails : vehicleDetailsArray) {

            vehicleDetailsRepo.save(vehicleDetails);
        }
        return true;
    }
}
