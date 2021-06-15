package com.example.trucker_demo.service;

import com.example.trucker_demo.model.VehicleReading_Model;
import com.example.trucker_demo.repository.VehicleReadingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleReadingService {

    @Autowired
    VehicleReadingsRepo vehicleReadingsRepo;

    public void saveAllVehicleReadings(VehicleReading_Model vehicleReading_model) {
        vehicleReadingsRepo.save(vehicleReading_model);
    }
}
