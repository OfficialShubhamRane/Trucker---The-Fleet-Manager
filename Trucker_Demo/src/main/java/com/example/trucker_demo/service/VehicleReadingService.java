package com.example.trucker_demo.service;

import com.example.trucker_demo.model.VehicleDetails_Model;
import com.example.trucker_demo.model.VehicleReading_Model;
import com.example.trucker_demo.repository.VehicleDetailsRepo;
import com.example.trucker_demo.repository.VehicleReadingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        /** Creating constructor to allot UUID to each reading */
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

    /** Returns all vehicle readings */
    public List<VehicleReading_Model> getAllVehicleReadings() {
        return vehicleReadingsRepo.findAll();
    }

    /** Returns vehicle locations over last 30 mins */
    public List<VehicleReading_Model> getVehicleCoordinates(String vin) {
        return vehicleReadingsRepo.findAllByVin(vin);
    }

    /** returning specific signal history of specific vehicles over user defined time range */
    public List<String> getSignalHistoryOver(String vin, String attribute, String minutes) {

        minutes = "+0 00:"+minutes+":00.000000000";
        List<VehicleReading_Model> vehicleReading_models =  vehicleReadingsRepo.findAllSignalReadings(vin, minutes);
        System.out.println("Vehicle model matching query: " + vehicleReading_models.get(0));
        List<String> requiredDataList = new ArrayList<String>();

        for( VehicleReading_Model vehicleReading_model : vehicleReading_models ){
            switch (attribute){
                case "latitude":
                    requiredDataList.add(String.valueOf(vehicleReading_model.getLatitude()));
                    break;
                case "longitude":
                    requiredDataList.add(String.valueOf(vehicleReading_model.getLongitude()));
                    break;
                case "fuelVolume":
                    requiredDataList.add(String.valueOf(vehicleReading_model.getFuelVolume()));
                    break;
                case "speed":
                    requiredDataList.add(vehicleReading_model.getSpeed());
                    break;
                case "engineHp":
                    requiredDataList.add(String.valueOf(vehicleReading_model.getEngineHp()));
                    break;
                case "checkEngineLightOn":
                    requiredDataList.add(String.valueOf(vehicleReading_model.isCheckEngineLightOn()));
                    break;
                case "engineCoolantLow":
                    requiredDataList.add(String.valueOf(vehicleReading_model.isEngineCoolantLow()));
                    break;
                case "cruiseControlOn":
                    requiredDataList.add(String.valueOf(vehicleReading_model.isCruiseControlOn()));
                    break;
                case "engineRpm":
                    requiredDataList.add(String.valueOf(vehicleReading_model.getEngineRpm()));
                    break;
                case "tires":
            }

        }
        return requiredDataList;

    }

}
