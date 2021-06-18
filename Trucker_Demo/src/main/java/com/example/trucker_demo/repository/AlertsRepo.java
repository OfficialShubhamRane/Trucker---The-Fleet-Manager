package com.example.trucker_demo.repository;

import com.example.trucker_demo.model.Alerts_Model;
import com.example.trucker_demo.model.VehicleDetails_Model;
import com.example.trucker_demo.model.VehicleReading_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertsRepo extends JpaRepository<Alerts_Model, String> {

}
