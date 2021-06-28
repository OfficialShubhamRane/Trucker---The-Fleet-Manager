package com.example.readingservice.controller;

import com.example.readingservice.model.VehicleLocation_Model;
import com.example.readingservice.model.VehicleReading_Model;
import com.example.readingservice.service.VehicleReading_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class VehicleReadings_Controller {

    @Autowired
    VehicleReading_Service vehicleReading_service;

    /** accepts vehicle readings */
    @PostMapping("/readings")
    public void postVehicleReadings(@RequestBody VehicleReading_Model vehicleReading_model){
        vehicleReading_service.saveVehicleReadings(vehicleReading_model);
    }

    /** returns vehicle readings */
    @GetMapping({"api/readings", "api/readings/{vin}"})
    public List<VehicleReading_Model> getVehicleReadings(
            @PathVariable(required = false) String vin
    ){
        return vehicleReading_service.getVehicleReadings(vin);
    }

    /** return vehicle location over last 30 mins */
    @GetMapping("/api/location/{vin}")
    public List<VehicleLocation_Model> getVehicleLocation(@PathVariable String vin) throws IOException, InterruptedException {
        return vehicleReading_service.getVehicleLocation(vin);
    }


}
