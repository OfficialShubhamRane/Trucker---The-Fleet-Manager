package com.example.detailservice.controller;

import com.example.detailservice.model.VehicleDetails_Model;
import com.example.detailservice.service.VehicleDetails_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VehicleDetails_Controller {

    @Autowired
    VehicleDetails_Service vehicleDetails_service;

    @PutMapping("/vehicles")
    public void putVehicleDetails(@RequestBody  VehicleDetails_Model[] vehicleDetails_models){
        vehicleDetails_service.saveVehicleDetails(vehicleDetails_models);
    }
}
