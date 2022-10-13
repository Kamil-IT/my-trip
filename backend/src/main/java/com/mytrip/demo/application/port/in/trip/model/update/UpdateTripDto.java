package com.mytrip.demo.application.port.in.trip.model.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTripDto {

    @NotNull
    UUID uuid;

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
