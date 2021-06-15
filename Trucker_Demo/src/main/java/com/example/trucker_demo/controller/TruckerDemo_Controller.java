package com.example.trucker_demo.controller;

import com.example.trucker_demo.model.VehicleDetails_Model;
import com.example.trucker_demo.model.VehicleReading_Model;
import com.example.trucker_demo.repository.VehicleDetailsRepo;
import com.example.trucker_demo.repository.VehicleReadingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;

@RestController
public class TruckerDemo_Controller {

    @Autowired
    VehicleDetailsRepo vehicleDetailsRepo;

    @Autowired
    VehicleReadingsRepo vehicleReadingsRepo;

    @PutMapping("/vehicles")
    @ResponseBody
    public VehicleDetails_Model[] postVehicleDetails(
            @RequestBody VehicleDetails_Model[] vehicleDetails_model){

        for (VehicleDetails_Model vehicleDetails : vehicleDetails_model) {
            vehicleDetailsRepo.save(vehicleDetails);
        }
        System.out.println(Arrays.toString(vehicleDetails_model));
        return vehicleDetails_model;

    }

    @PostMapping("/readings")
    @ResponseBody
    public VehicleReading_Model postVehicleReadings(
            @RequestBody VehicleReading_Model vehicleReading_model){

        vehicleReadingsRepo.save(vehicleReading_model);

        System.out.println(vehicleReading_model);
        return  vehicleReading_model;
    }


}
