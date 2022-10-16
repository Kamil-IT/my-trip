package com.mytrip.demo.application.port.in.trip.model.create;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
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
    String creator;

//  trip  Events can be add here -> thig abut it
}
