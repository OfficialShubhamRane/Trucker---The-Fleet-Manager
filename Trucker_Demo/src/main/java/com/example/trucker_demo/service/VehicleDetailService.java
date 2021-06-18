package com.example.trucker_demo.service;

import com.example.trucker_demo.model.VehicleDetails_Model;
import com.example.trucker_demo.repository.VehicleDetailsRepo;
import com.example.trucker_demo.repository.VehicleReadingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class VehicleDetailService {

    @Autowired
    VehicleDetailsRepo vehicleDetailsRepo;

    public void saveAllVehicleDetails(VehicleDetails_Model[] vehicleDetailsArray){

        for (VehicleDetails_Model vehicleDetails : vehicleDetailsArray) {
//            System.out.println( vehicleDetails );
            vehicleDetailsRepo.save(vehicleDetails);
        }
    }

    public List<VehicleDetails_Model> getAllVehicleDetails() {
        return vehicleDetailsRepo.findAll();
    }


    public void showVehicleDetails(Model model, VehicleDetails_Model[] vehicleDetails_models) {
        model.addAttribute("vehicleDetailsArray",vehicleDetails_models);
    }


}
