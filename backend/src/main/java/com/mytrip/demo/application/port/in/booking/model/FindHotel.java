package com.mytrip.demo.application.port.in.booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class FindHotel {

    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    @Positive
    private Integer adultsNumber;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate from;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate to;
}
