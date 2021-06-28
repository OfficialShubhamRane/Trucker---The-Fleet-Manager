package com.example.detailservice.repository;

import com.example.detailservice.model.VehicleDetails_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDetails_Repository extends JpaRepository<VehicleDetails_Model, String> {

    /** Returns vehicle details by vin */
    VehicleDetails_Model findByVin(String vin);
}
