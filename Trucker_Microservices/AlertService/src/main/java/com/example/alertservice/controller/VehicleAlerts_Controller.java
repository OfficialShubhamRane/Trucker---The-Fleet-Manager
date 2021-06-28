package com.example.alertservice.controller;

import com.example.alertservice.model.VehicleAlerts_Model;
import com.example.alertservice.service.VehicleAlerts_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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

    @Autowired
    DiscoveryClient discoveryClient;

    /** returns alerts for vin */
    @GetMapping("api/alerts/{vin}")
    public List<VehicleAlerts_Model> getVehicleAlerts(
            @RequestParam Optional<String> sortBy,
            @PathVariable(required = false) String vin
    ){
        return vehicleAlerts_service.getVehicleAlerts(vin);
    }

    @GetMapping("/service/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }
}
