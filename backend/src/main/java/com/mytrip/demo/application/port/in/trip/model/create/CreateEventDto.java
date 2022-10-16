package com.mytrip.demo.application.port.in.trip.model.create;

import com.mytrip.demo.application.persistance.trip.model.event.type.TripEventTypeName;
import lombok.*;
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
public class CreateEventDto {

    @NotBlank
    String title;
    String locationDescription;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate from;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate to;
    @NotNull
    TripEventTypeName type;

    @NotNull
    UUID tripId;

    String creator;

    List<Map<String, String>> properties;
}
