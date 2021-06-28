package com.example.detailservice.controller;

import com.example.detailservice.model.VehicleDetails_Model;
import com.example.detailservice.service.VehicleDetails_Service;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleDetails_Controller {

    @Autowired
    VehicleDetails_Service vehicleDetails_service;

    @Autowired
    DiscoveryClient discoveryClient;

    /** Accepts vehicle details array */
    @PutMapping("/vehicles")
    public void putVehicleDetails(@RequestBody  VehicleDetails_Model[] vehicleDetails_models){
        vehicleDetails_service.saveVehicleDetails(vehicleDetails_models);
    }

    /** returns vehicle details */
    @GetMapping({"api/vehicles/","api/vehicles/{vin}"})
    @ResponseBody
    public List<VehicleDetails_Model> getVehicleDetails(@PathVariable(required = false) String vin){
        System.out.println("vin: " + vin);
        return vehicleDetails_service.getVehicleDetails(vin);
    }

    @RequestMapping("/service/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }
}
