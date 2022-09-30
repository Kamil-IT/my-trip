package com.mytrip.demo.application.port.in.trip.model;

import com.mytrip.demo.application.persistance.trip.event.type.TripEventTypeName;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class CreateEventDto {

    @NotBlank
    String title;
    Double latitude;
    Double longitude;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate from;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate to;
    @NotNull
    TripEventTypeName type;

    @NotNull
    UUID eventId;
}
