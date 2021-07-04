package com.example.detailservice.controller;

import com.example.detailservice.model.VehicleDetails_Model;
import com.example.detailservice.service.VehicleDetails_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VehicleDetails_Controller {

    @Autowired
    VehicleDetails_Service vehicleDetails_service;

    /** Accepts vehicle details array */
    @PutMapping("/vehicles")
    public void putVehicleDetails(@RequestBody VehicleDetails_Model[] vehicleDetails_models){
        vehicleDetails_service.saveVehicleDetails(vehicleDetails_models);
    }

    /** returns specific vehicle details */
    @GetMapping({"api/vehicles/","api/vehicles/{vin}"})
    @ResponseBody
    public VehicleDetails_Model getVehicleDetails(@PathVariable(required = false) String vin){
        System.out.println("Asking for vin: " + vin);
        return vehicleDetails_service.getVehicleDetails(vin);
    }

}
