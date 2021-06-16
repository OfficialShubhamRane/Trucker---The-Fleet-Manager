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

    public void saveAllVehicleDetails(VehicleDetails_Model vehicleDetails){

        vehicleDetailsRepo.save(vehicleDetails);
    }
}
