package com.br.parkingcontrol.service;

import com.br.parkingcontrol.dto.ParkingDto;
import com.br.parkingcontrol.models.Parking;
import com.br.parkingcontrol.repositories.ParkingRepository;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return parkingRepository.existsByLicensePlateCar(licensePlateCar);
    }

    public boolean existsByParkingNumber(String parkingNumber) {
        return parkingRepository.existsByParkingNumber(parkingNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return parkingRepository.existsByApartmentAndBlock(apartment, block);
    }

    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    public Optional findById(UUID id) {
        return parkingRepository.findById(id);
    }

    @Transactional
    public void delete(Parking parking) {
        parkingRepository.delete(parking);
    }

}
