package com.example.readingservice.repository;

import com.example.readingservice.model.VehicleReading_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleReadings_Repository extends JpaRepository<VehicleReading_Model, String> {

    /** Returns vehicle readings by vin */
    List<VehicleReading_Model> findAllByVin(String vin);

    /** Checks if readings exists for given vin */
    boolean existsByVin(String vin);
}
