package com.mytrip.demo.domain;

import com.mytrip.demo.application.persistance.trip.event.type.TripEventTypeName;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Value
@Builder
public class Event {
    UUID uuid;
    String title;
    LocalDate from;
    LocalDate to;
    LocationDetails location;
    String creatorEmail;
    TripEventTypeName eventType;
    Map<String, String> properties;

    @Value
    @Builder
    public static class LocationDetails {

        Double latitude;
        Double longitude;
        String locationDescription;
    }
}
