package com.mytrip.demo.application.port.in.trip.model;

import com.mytrip.demo.domain.Trip;
import lombok.Value;

import java.util.List;

@Value
public class GetTripsDto {

    List<Trip> trips;
}
