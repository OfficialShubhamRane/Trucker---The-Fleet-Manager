package com.example.alertservice.repository;

import com.example.alertservice.model.VehicleDetails_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDetails_Repository extends JpaRepository<VehicleDetails_Model, String> {

    /** Returns vehicle details by vin */
    VehicleDetails_Model findByVin(String vin);
}
