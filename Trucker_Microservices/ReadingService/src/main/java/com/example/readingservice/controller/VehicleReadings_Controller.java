package com.example.readingservice.controller;

import com.example.readingservice.model.VehicleAlerts_Model;
import com.example.readingservice.model.VehicleDetail_Model;
import com.example.readingservice.model.VehicleLocation_Model;
import com.example.readingservice.model.VehicleReading_Model;
import com.example.readingservice.service.VehicleReading_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class VehicleReadings_Controller {

    @Autowired
    VehicleReading_Service vehicleReading_service;

    @Autowired
    RestTemplate restTemplate;

    /** accepts vehicle readings */
    @PostMapping("/readings")
    public void postVehicleReadings(@RequestBody VehicleReading_Model vehicleReading_model){
        vehicleReading_service.saveVehicleReadings(vehicleReading_model);

        /** Gets Vehicle details to call for comparison before Alerts */
        VehicleDetail_Model vehicleDetail_model = restTemplate.getForObject("http://localhost:8082/api/vehicles/"+vehicleReading_model.getVin(),VehicleDetail_Model.class);
        VehicleAlerts_Model[] vehicleAlerts_modelsArray = restTemplate.getForObject("http://localhost:8083/api/alerts/"+vehicleDetail_model.getVin()+"/"+vehicleReading_model.getVin(), VehicleAlerts_Model[].class);
        System.out.println(Arrays.toString(vehicleAlerts_modelsArray));
    }

    /** returns vehicle readings */
    @GetMapping({"api/readings", "api/readings/{vin}"})
    public List<VehicleReading_Model> getVehicleReadings(@PathVariable(required = false) String vin){
        return vehicleReading_service.getVehicleReadings(vin);
    }

    /** returns vehicle location over last 30 mins */
    @GetMapping("/api/location/{vin}")
    public List<VehicleLocation_Model> getVehicleLocation(@PathVariable String vin) throws IOException, InterruptedException {
        return vehicleReading_service.getVehicleLocation(vin);
    }

}
