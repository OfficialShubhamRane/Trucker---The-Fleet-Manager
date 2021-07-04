package com.example.detailservice.service;

import com.example.detailservice.model.VehicleDetails_Model;
import com.example.detailservice.repository.VehicleDetails_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleDetails_Service {

    @Autowired
    VehicleDetails_Repository vehicleDetails_repository;

    /** saves all vehicle details */
    public void saveVehicleDetails(VehicleDetails_Model[] vehicleDetails_models) {

        for (VehicleDetails_Model vehicleDetails : vehicleDetails_models) {
            System.out.println( vehicleDetails );
            vehicleDetails_repository.save(vehicleDetails);
        }

    }

    /** returns vehicle details */
    public VehicleDetails_Model getVehicleDetails(String vin) {

//        if(vin == null){
//            vehicleDetails_list = vehicleDetails_repository.findAll();
//        }else
        if( vehicleDetails_repository.existsById(vin) ){
            return vehicleDetails_repository.findByVin(vin);
        }else{
            System.out.println("No details exists for this vin");
        }
        return null;
    }

}