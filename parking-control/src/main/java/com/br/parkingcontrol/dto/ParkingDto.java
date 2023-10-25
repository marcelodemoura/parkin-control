package com.br.parkingcontrol.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ParkingDto {

    @NotBlank
    private String parkingNumber;
    @NotBlank
    @Size(max = 7)
    private String licensePlateCar;
    @NotBlank
    private String brandCar;
    @NotBlank
    private String modelCar;
    @NotBlank
    private String collorCar;
    @NotBlank
    private String responsibleName;
    @NotBlank
    private String apartamnet;
    @NotBlank
    private String block;

}
