package com.br.parkingcontrol.controller;


import com.br.parkingcontrol.dto.ParkingDto;
import com.br.parkingcontrol.models.Parking;
import com.br.parkingcontrol.service.ParkingService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(name = "/parking")
public class ParkingController {

    final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @PostMapping
    public ResponseEntity<Object>saveParking(@RequestBody @Valid ParkingDto parkingDto){
        var parking = new Parking();
        BeanUtils.copyProperties(parkingDto, parking);
        parking.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingService.save(parking));
    }




}
