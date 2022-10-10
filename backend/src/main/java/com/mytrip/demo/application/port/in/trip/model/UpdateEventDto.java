package com.mytrip.demo.application.port.in.trip.model;

import com.mytrip.demo.application.persistance.trip.event.type.TripEventTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateEventDto {

    @NotBlank
    String title;
    @NotBlank
    String locationDescription;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate from;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate to;
}
