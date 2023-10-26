package com.br.parkingcontrol.repositories;

import com.br.parkingcontrol.models.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ParkingRepository extends JpaRepository<Parking, UUID> {

    boolean existsByLicensePlateCar(String licensePlateCar);
    boolean existsByParkingNumber(String parkingNumber);
    boolean existsByApartmentAndBlock(String Apartment,String block);



}
