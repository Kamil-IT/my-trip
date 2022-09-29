package com.mytrip.demo.application.port.in.trip.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Builder
public class CreateTripDto {

    @NotBlank
    String title;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate from;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate to;

//  trip  Events can be add here -> thig abut it
}
