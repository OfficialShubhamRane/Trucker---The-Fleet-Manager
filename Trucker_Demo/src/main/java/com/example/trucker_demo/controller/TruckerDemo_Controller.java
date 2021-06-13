package com.example.trucker_demo.controller;

import com.example.trucker_demo.model.VehicleDetails_Model;
import com.example.trucker_demo.model.VehicleReading_Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TruckerDemo_Controller {

    @Autowired
    VehicleDetails_Model vehicleDetails_model;

    @GetMapping("/vehicle")
    @ResponseBody
    public VehicleDetails_Model getVehicleDetails(){
        vehicleDetails_model.setVin("7dg38fhf62ifj");
        vehicleDetails_model.setModel("Honda Accord");
        vehicleDetails_model.setMake("2019");
        vehicleDetails_model.setMaxFuelVolumn(15);

        System.out.println(vehicleDetails_model);
        return vehicleDetails_model;
    }

    @PostMapping("/readings")
    @ResponseBody
    public VehicleReading_Model postVehicleReadings(
            @RequestBody VehicleReading_Model vehicleReading_model
    ){
        System.out.println(vehicleReading_model);
        return  vehicleReading_model;
    }

}
