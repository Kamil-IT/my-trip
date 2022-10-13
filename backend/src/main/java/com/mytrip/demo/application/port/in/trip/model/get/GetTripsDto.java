package com.mytrip.demo.application.port.in.trip.model.get;

import com.mytrip.demo.domain.Trip;
import lombok.Value;

import java.util.List;

@Value
public class GetTripsDto {

    List<Trip> trips;
}
