package com.example.trucker_demo.controller;

import com.example.trucker_demo.model.VehicleDetails_Model;
import com.example.trucker_demo.model.VehicleReading_Model;
import com.example.trucker_demo.service.VehicleDetailService;
import com.example.trucker_demo.service.VehicleReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TruckerDemo_Controller {

    @Autowired
    VehicleDetailService vehicleDetailsService;

    @Autowired
    VehicleReadingService vehicleReadingsService;

    @PutMapping("/vehicles")
    @ResponseBody
    public boolean postVehicleDetails(
            @RequestBody VehicleDetails_Model[] vehicleDetails_model){
        return vehicleDetailsService.saveAllVehicleDetails(vehicleDetails_model);
    }

    @PostMapping("/readings")
    @ResponseBody
    public boolean postVehicleReadings(
            @RequestBody VehicleReading_Model vehicleReading_model){

//        System.out.println(vehicleReading_model);
        return vehicleReadingsService.saveAllVehicleReadings(vehicleReading_model);
    }

    @GetMapping("/api/vehicles")
    public List<VehicleDetails_Model> getAllVehicleDetails_models(){
        return vehicleDetailsService.getAllVehicleDetails();
    }

    @GetMapping("/api/readings")
    public List<VehicleReading_Model> getAllVehicleReading_models(){
        return vehicleReadingsService.getAllVehicleReadings();
    }

    /** Front end under construction  */
    @GetMapping("/Trucker")
    @ResponseBody
    public String showIndexPage(Model model, VehicleDetails_Model[] vehicleDetails_models){
//        vehicleDetailsService.showVehicleDetails(model, vehicleDetails_models);
        return "index";
    }



}
