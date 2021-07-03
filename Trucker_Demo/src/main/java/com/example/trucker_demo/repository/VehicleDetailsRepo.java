package com.example.trucker_demo.repository;

import com.example.trucker_demo.model.VehicleDetails_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDetailsRepo extends JpaRepository<VehicleDetails_Model, String> {

    /** returns specific vehicle details using VIN as key */
    VehicleDetails_Model findByVin(String vin);

    /** returns specific vehicle details using VIN as key */
    VehicleDetails_Model findAllByVin(String vin);
}
