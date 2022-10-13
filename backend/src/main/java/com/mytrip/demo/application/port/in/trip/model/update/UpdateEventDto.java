package com.mytrip.demo.application.port.in.trip.model.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

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
