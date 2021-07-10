package com.example.trucker_demo.controller;

import com.example.trucker_demo.model.Alerts_Model;
import com.example.trucker_demo.model.VehicleDetails_Model;
import com.example.trucker_demo.model.VehicleLocation_Model;
import com.example.trucker_demo.model.VehicleReading_Model;
import com.example.trucker_demo.service.AlertsService;
import com.example.trucker_demo.service.RevGeoLocationService;
import com.example.trucker_demo.service.VehicleDetailService;
import com.example.trucker_demo.service.VehicleReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
public class TruckerDemo_Controller {

    @Autowired
    VehicleDetailService vehicleDetailsService;

    @Autowired
    VehicleReadingService vehicleReadingsService;

    @Autowired
    AlertsService alertsService;

    @Autowired
    RevGeoLocationService revGeoLocationService;

    /** Front end index page */
    @GetMapping("/")
    public String getIndex(){
        System.out.println("Inside the index");
        return "index";
    }

    /** 1.1 , 1.2 Accepts vehicle details from http://localhost:8080/vehicles */
    @PutMapping("/vehicles")
    @ResponseBody
    public void postVehicleDetails(
            @RequestBody VehicleDetails_Model[] vehicleDetails_model){
        vehicleDetailsService.saveAllVehicleDetails(vehicleDetails_model);
    }

    /** 1.3 , 3.6 Accepts vehicle readings from http://localhost:8080/readings */
    @PostMapping("/readings")
    @ResponseBody
    public void postVehicleReadings(
            @RequestBody VehicleReading_Model vehicleReading_model){
        vehicleReadingsService.saveAllVehicleReadings(vehicleReading_model);
    }

    /** 3.1 Returns all vehicles all details */
    @GetMapping("/api/vehicles")
    public List<VehicleDetails_Model> getAllVehiclesDetails(){
        return vehicleDetailsService.getAllVehicleDetails();
    }

    /** 3.2 returning all vehicles HIGH alerts from last 2 hours and sort if wanted */
    @GetMapping({"api/getRecentAlerts","api/getRecentAlerts/{orderBy}"})
    public List<Alerts_Model> getRecentAlerts(@PathVariable(required = false) String orderBy){
        return alertsService.getRecentAlerts(orderBy);
    }

    /** 3.3 Returning specific signal history of specific vehicles over user defined time range */
    @GetMapping("api/getSignalHistoryOver/{vin}/{attribute}/{minutes}")
    public List<String> getSignalHistoryOver(
            @PathVariable String vin,
            @PathVariable String attribute,
            @PathVariable String minutes){
        return vehicleReadingsService.getSignalHistoryOver(vin, attribute, minutes);
    }

    /** 3.4 Vehicle's location in last 30 mins */
    @GetMapping("/api/getVehicleLocation/{vin}")
    public List<VehicleLocation_Model> getVehicleLocation(
            @PathVariable("vin") String vin
    ) throws IOException, InterruptedException {
        return revGeoLocationService.getVehicleLocation(vin);
    }

    /** 3.5 Returns all vehicles all alerts OR specified vehicles all alerts */
    @GetMapping({"/api/alerts", "api/alerts/{vin}"})
    public List<Alerts_Model> getAllAlerts( @PathVariable(required = false) String vin){
        if(vin == null){
            return alertsService.getAllAlerts();
        }
        else {
            return alertsService.getVehicleAlerts(vin);
        }
    }


//    /** Debugging: Returns all vehicles all readings */
//    @GetMapping("/api/readings")
//    public List<VehicleReading_Model> getAllVehiclesReading(){
//        return vehicleReadingsService.getAllVehicleReadings();
//    }
//
//    /** Debugging: Returns specific vehicles all details using VIN as key */
//    @GetMapping("/api/details/{vin}")
//    public VehicleDetails_Model getVehicleDetails(@PathVariable("vin") String vin){
//        return vehicleDetailsService.getVehicleDetails(vin);
//    }
//
//    /** 3.4 Demo */
//    @GetMapping("api/getGeoCode")
//    public void getRevGeoCoding() throws IOException, InterruptedException {
//
//        ObjectMapper mapper = new ObjectMapper();
//        String response = revGeoLocationService.revGeoCode("41.803194,-88.144406");
////        System.out.println(response);
//        JsonNode responseJsonNode = mapper.readTree(response);
//        JsonNode items = responseJsonNode.get("items");
//
//        for (JsonNode item : items) {
//            JsonNode address = item.get("address");
//            String label = address.get("label").asText();
//            JsonNode position = item.get("position");
//
//            String lat = position.get("lat").asText();
//            String lng = position.get("lng").asText();
//            System.out.println(label + " is located at " + lat + "," + lng + ".");
//        }
//
//    }

}
