package com.br.parkingcontrol.service;

import com.br.parkingcontrol.models.Parking;
import com.br.parkingcontrol.repositories.ParkingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParkingService {
    final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Transactional
    public Parking save(Parking parking) {
        return parkingRepository.save(parking);

    }
}
