package com.br.parkingcontrol.controller;


import com.br.parkingcontrol.dto.ParkingDto;
import com.br.parkingcontrol.models.Parking;
import com.br.parkingcontrol.service.ParkingService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(name = "/parking")
public class ParkingController {

    final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @PreAuthorize("hasRole('Role_Admin')")
    @PostMapping
    public ResponseEntity<Object> saveParking(@RequestBody @Valid ParkingDto parkingDto) {
        if (parkingService.existsByLicensePlateCar(parkingDto.getLicensePlateCar())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: license Plate car exists");
        }
        if (parkingService.existsByParkingNumber(parkingDto.getParkingNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("conflict parking is already in use!");
        }
        if (parkingService.existsByApartmentAndBlock(parkingDto.getApartment(), parkingDto.getBlock())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("conflict:Parking already registered for this apartmant/block");

        }
        var parking = new Parking();
        BeanUtils.copyProperties(parkingDto, parking);
        parking.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingService.save(parking));
    }


    @PreAuthorize("hasRole('Role_User','Role_Admin')")
    @GetMapping("/todos")
    public ResponseEntity<List<Parking>> getAllParking() {
        return ResponseEntity.status(HttpStatus.OK).body(parkingService.findAll());
    }

    @PreAuthorize("hasRole('Role_User','Role_Admin')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getIdParking(@PathVariable(value = "id") UUID id) {
        Optional<Parking> parkingOptional = parkingService.findById(id);
        if (!parkingOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingOptional.get());
    }

    @PreAuthorize("hasRole('Role_Admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParking(@PathVariable(value = "id") UUID id) {
        Optional<Parking> parkingOptional = parkingService.findById(id);
        if (!parkingOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("C");
        }
        parkingService.delete(parkingOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Successfully");

    }

    @PreAuthorize("hasRole('Role_Admin')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParking(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ParkingDto parkingDto) {
        Optional<Parking> parkingOptional = parkingService.findById(id);
        if (!parkingOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("Parking not found"));
        }
        var parking = new Parking();
        BeanUtils.copyProperties(parkingDto, parking);
        parking.setId(parkingOptional.get().getId());
        parking.setRegistrationDate(parkingOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(parkingService.save(parking));
    }
}




