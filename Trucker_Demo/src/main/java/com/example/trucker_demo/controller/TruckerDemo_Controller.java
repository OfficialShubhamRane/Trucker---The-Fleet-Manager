package com.example.trucker_demo.controller;

import com.example.trucker_demo.model.VehicleDetails_Model;
import com.example.trucker_demo.model.VehicleReading_Model;
import com.example.trucker_demo.repository.VehicleDetailsRepo;
import com.example.trucker_demo.repository.VehicleReadingsRepo;
import com.example.trucker_demo.service.VehicleDetailService;
import com.example.trucker_demo.service.VehicleReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@RestController
public class TruckerDemo_Controller {

    @Autowired
    VehicleDetailService vehicleDetailsService;

    @Autowired
    VehicleReadingService vehicleReadingsService;

    @PutMapping("/vehicles")
    @ResponseBody
    public VehicleDetails_Model[] postVehicleDetails(
            @RequestBody VehicleDetails_Model[] vehicleDetails_model){

        vehicleDetailsService.saveAllVehicleDetails(vehicleDetails_model);
        return vehicleDetails_model;

    }

    @PostMapping("/readings")
    @ResponseBody
    public VehicleReading_Model postVehicleReadings(
            @RequestBody VehicleReading_Model vehicleReading_model){

//        System.out.println(vehicleReading_model);
        vehicleReadingsService.saveAllVehicleReadings(vehicleReading_model);
        return  vehicleReading_model;
    }

    @GetMapping("/api/vehicles")
    public List<VehicleDetails_Model> getAllVehicleDetails_models(){
        return vehicleDetailsService.getAllVehicleDetails();
    }

    @GetMapping("/api/readings")
    public List<VehicleReading_Model> getAllVehicleReading_models(){
        return vehicleReadingsService.getAllVehicleReadings();
    }

}
