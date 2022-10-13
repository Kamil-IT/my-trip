package com.mytrip.demo.domain;

import com.mytrip.demo.application.persistance.trip.model.event.type.TripEventTypeName;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.*;

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
    Set<Property> properties;
    List<String> participants;

    @Value
    @Builder
    public static class LocationDetails {
        Double latitude;
        Double longitude;
        String locationDescription;
    }

    @Value
    @Builder
    public static class Property {
        String key;
        String value;
    }
}
