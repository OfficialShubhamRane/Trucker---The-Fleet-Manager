package com.example.trucker_demo.service;

import com.example.trucker_demo.model.VehicleDetails_Model;
import com.example.trucker_demo.model.VehicleReading_Model;
import com.example.trucker_demo.repository.VehicleDetailsRepo;
import com.example.trucker_demo.repository.VehicleReadingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleReadingService {

    @Autowired
    VehicleReadingsRepo vehicleReadingsRepo;

    @Autowired
    VehicleDetailsRepo vehicleDetailsRepo;

    @Autowired
    AlertsService alertsService;

    /** Save all vehicles all readings */
    public void saveAllVehicleReadings(VehicleReading_Model vehicleReading_model) {

        VehicleDetails_Model vehicleDetails_model = vehicleDetailsRepo.getById( vehicleReading_model.getVin() );

        alertsService.checkForAlerts(vehicleDetails_model, vehicleReading_model );

        vehicleReadingsRepo.save(vehicleReading_model);

    }

    /** REST api for getting all vehicles all readings */
    public List<VehicleReading_Model> getAllVehicleReadings() {
        return vehicleReadingsRepo.findAll();
    }

    /** returning specific signal history of specific vehicles over user defined time range */
    /*
    public List<VehicleReading_Model> getSignalHistoryOver(String vin, String attribute, String minutes) {
        return vehicleReadingsRepo.findAllSignalReadings(vin, attribute, minutes);
    }
    */
}
