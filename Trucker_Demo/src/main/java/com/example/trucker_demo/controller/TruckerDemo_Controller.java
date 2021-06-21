package com.example.trucker_demo.controller;

import com.example.trucker_demo.model.Alerts_Model;
import com.example.trucker_demo.model.VehicleDetails_Model;
import com.example.trucker_demo.model.VehicleReading_Model;
import com.example.trucker_demo.service.AlertsService;
import com.example.trucker_demo.service.VehicleDetailService;
import com.example.trucker_demo.service.VehicleReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TruckerDemo_Controller {

    @Autowired
    VehicleDetailService vehicleDetailsService;

    @Autowired
    VehicleReadingService vehicleReadingsService;

    @Autowired
    AlertsService alertsService;

    /** Accepts vehicle details from http://localhost:8080/vehicles */
    @PutMapping("/vehicles")
    @ResponseBody
    public void postVehicleDetails(
            @RequestBody VehicleDetails_Model[] vehicleDetails_model){
        vehicleDetailsService.saveAllVehicleDetails(vehicleDetails_model);
    }

    /** Accepts vehicle details from http://localhost:8080/readings */
    @PostMapping("/readings")
    @ResponseBody
    public void postVehicleReadings(
            @RequestBody VehicleReading_Model vehicleReading_model){

        vehicleReadingsService.saveAllVehicleReadings(vehicleReading_model);
    }

    /** REST API: Returns all vehicles all details */
    @GetMapping("/api/vehicles")
    public List<VehicleDetails_Model> getAllVehiclesDetails(){
        return vehicleDetailsService.getAllVehicleDetails();
    }

    /** REST API: Returns all vehicles all readings */
    @GetMapping("/api/readings")
    public List<VehicleReading_Model> getAllVehiclesReading(){
        return vehicleReadingsService.getAllVehicleReadings();
    }

    /** REST API: Returns all vehicles all alerts */
    @GetMapping("/api/alerts")
    public List<Alerts_Model> getAllAlerts(){
        return alertsService.getAllAlerts();
    }

    /** REST API: Returns specific vehicles all details using VIN as key */
    @GetMapping("/api/getVehicleDetails/{vin}")
    public VehicleDetails_Model getVehicleDetails(@PathVariable("vin") String vin){
        return vehicleDetailsService.getVehicleDetails(vin);
    }

    /** REST API: Returns specific vehicles all alerts using VIN as key */
    @GetMapping("api/getVehicleAlerts/{vin}")
    public List<Alerts_Model> getVehicleAlerts(@PathVariable("vin") String vin){
        return alertsService.getVehicleAlerts(vin);
    }

    /** REST API for returning all vehicles HIGH alerts from last 2 hours */
    @GetMapping("api/getRecentAlerts")
    public List<Alerts_Model> getRecentAlerts(){
        return alertsService.getRecentAlerts();
    }




}
