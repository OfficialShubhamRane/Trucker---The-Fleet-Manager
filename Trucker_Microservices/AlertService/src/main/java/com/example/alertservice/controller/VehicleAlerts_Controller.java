package com.example.alertservice.controller;

import com.example.alertservice.model.VehicleAlerts_Model;
import com.example.alertservice.model.VehicleDetails_Model;
import com.example.alertservice.model.VehicleReading_Model;
import com.example.alertservice.service.VehicleAlerts_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class VehicleAlerts_Controller {

    @Autowired
    VehicleAlerts_Service vehicleAlerts_service;

//    /** returns alerts for vin */
//    @GetMapping("api/alerts/{vin}")
//    public List<VehicleAlerts_Model> getVehicleAlerts(
//            @RequestParam Optional<String> sortBy,
//            @PathVariable(required = false) String vin
//    ){
//        return vehicleAlerts_service.getVehicleAlerts(vin);
//    }

    /** returns alerts for vin */
    @GetMapping("api/alerts/{vehicleDetail_vin}/{vehicleReading_vin}")
    public List<VehicleAlerts_Model> getVehicleAlerts(
            @PathVariable String vehicleDetail_vin,
            @PathVariable String vehicleReading_vin
    ){
        System.out.println(vehicleDetail_vin);
        System.out.println(vehicleReading_vin);
        return vehicleAlerts_service.checkForAlerts(vehicleDetail_vin, vehicleReading_vin);
    }

}
