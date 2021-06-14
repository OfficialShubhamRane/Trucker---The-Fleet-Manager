package com.example.trucker_demo.controller;

import com.example.trucker_demo.model.VehicleDetails_Model;
import com.example.trucker_demo.model.VehicleReading_Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class TruckerDemo_Controller {


//    @GetMapping("/vehicle")
//    @ResponseBody
//    public VehicleDetails_Model getVehicleDetails(){
//        VehicleDetails_Model vehicleDetails_model = new VehicleDetails_Model();
//        vehicleDetails_model.setVin("7dg38fhf62ifj");
//        vehicleDetails_model.setModel("Honda Accord");
//        vehicleDetails_model.setMake("2019");
//        vehicleDetails_model.setMaxFuelVolumn(15);
//
//        System.out.println(vehicleDetails_model);
//        return vehicleDetails_model;
//    }

    @PostMapping("/readings")
    @ResponseBody
    public VehicleReading_Model postVehicleReadings(
            @RequestBody VehicleReading_Model vehicleReading_model){

        System.out.println(vehicleReading_model);
        return  vehicleReading_model;
    }

    /** under construction */
    @PutMapping("/vehicles")
    @ResponseBody
    public VehicleDetails_Model[] postVehicleDetails(
            @RequestBody VehicleDetails_Model[] vehicleDetails_model){

        System.out.println(Arrays.toString(vehicleDetails_model));
        return vehicleDetails_model;

    }

}
