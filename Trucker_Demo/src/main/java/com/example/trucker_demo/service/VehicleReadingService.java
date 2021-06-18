package com.example.trucker_demo.service;

import com.example.trucker_demo.model.VehicleDetails_Model;
import com.example.trucker_demo.model.VehicleReading_Model;
import com.example.trucker_demo.repository.AlertsRepo;
import com.example.trucker_demo.repository.VehicleDetailsRepo;
import com.example.trucker_demo.repository.VehicleReadingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.persistence.Basic;
import java.util.List;

@Service
public class VehicleReadingService {

    @Autowired
    VehicleReadingsRepo vehicleReadingsRepo;

    @Autowired
    VehicleDetailsRepo vehicleDetailsRepo;

    @Autowired
    AlertsService alertsService;

    public void saveAllVehicleReadings(VehicleReading_Model vehicleReading_model) {

        VehicleDetails_Model vehicleDetails_model = vehicleDetailsRepo.getById( vehicleReading_model.getVin() );

        alertsService.checkForAlerts(vehicleDetails_model, vehicleReading_model );

        vehicleReadingsRepo.save(vehicleReading_model);

    }

    public List<VehicleReading_Model> getAllVehicleReadings() {

        return vehicleReadingsRepo.findAll();
    }
}
