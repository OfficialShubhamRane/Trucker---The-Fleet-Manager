package com.example.readingservice.repository;

import com.example.readingservice.model.VehicleReading_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleReadings_Repository extends JpaRepository<VehicleReading_Model, String> {

    /** Returns vehicle readings by vin */
    List<VehicleReading_Model> findAllByVin(String vin);

    /** Checks if readings exists for given vin */
    boolean existsByVin(String vin);

    /** Returns vehicles location in last 30 mins */
    @Query("SELECT r FROM VehicleReading_Model r WHERE r.timestamp <= (CURRENT_TIMESTAMP - '+0 00:30:00.000000000') AND r.vin = ?1")
    List<VehicleReading_Model> getVehicleLocation(String vin);
}
