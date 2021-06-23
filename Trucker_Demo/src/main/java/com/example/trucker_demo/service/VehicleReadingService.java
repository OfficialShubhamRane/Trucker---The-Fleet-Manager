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

        VehicleDetails_Model vehicleDetails_model = vehicleDetailsRepo.findByVin( vehicleReading_model.getVin() );

        VehicleReading_Model tempReadingModel = new VehicleReading_Model();
        tempReadingModel.setVin(vehicleReading_model.getVin());
        tempReadingModel.setLatitude(vehicleReading_model.getLatitude());
        tempReadingModel.setLongitude(vehicleReading_model.getLongitude());
        tempReadingModel.setTimestamp(vehicleReading_model.getTimestamp());
        tempReadingModel.setFuelVolume(vehicleReading_model.getFuelVolume());
        tempReadingModel.setSpeed(vehicleReading_model.getSpeed());
        tempReadingModel.setEngineHp(vehicleReading_model.getEngineHp());
        tempReadingModel.setCheckEngineLightOn(vehicleReading_model.isCheckEngineLightOn());
        tempReadingModel.setEngineCoolantLow(vehicleReading_model.isEngineCoolantLow());
        tempReadingModel.setCruiseControlOn(vehicleReading_model.isCruiseControlOn());
        tempReadingModel.setEngineRpm(vehicleReading_model.getEngineRpm());
        tempReadingModel.setTires(vehicleReading_model.getTires());

        alertsService.checkForAlerts(vehicleDetails_model, tempReadingModel );

        vehicleReadingsRepo.save(tempReadingModel);

    }

    /** REST api for getting all vehicles all readings */
    public List<VehicleReading_Model> getAllVehicleReadings() {
        return vehicleReadingsRepo.findAll();
    }

    /** Rest api for getting vehicles co ordinates over last 30 mins */
    public List<VehicleReading_Model> getVehicleCoordinates(String vin) {
        return vehicleReadingsRepo.findAllByVin(vin);
    }

//    /** returning specific signal history of specific vehicles over user defined time range */
//    public List<VehicleReading_Model> getSignalHistoryOver(String vin, String attribute, String minutes) {
//        return vehicleReadingsRepo.findAllSignalReadings(vin, attribute, minutes);
//    }

}
