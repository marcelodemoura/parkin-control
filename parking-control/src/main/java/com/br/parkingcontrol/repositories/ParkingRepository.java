package com.br.parkingcontrol.repositories;

import com.br.parkingcontrol.models.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParkingRepository extends JpaRepository<Parking, UUID> {
}
