package com.example.alertservice.repository;

import com.example.alertservice.model.VehicleAlerts_Model;
import com.example.alertservice.model.VehicleReading_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleAlerts_Repository extends JpaRepository<VehicleAlerts_Model, String> {

    List<VehicleReading_Model> findByVin(String vin);
}
