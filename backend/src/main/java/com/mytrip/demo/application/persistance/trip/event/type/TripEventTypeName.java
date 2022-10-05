package com.mytrip.demo.application.persistance.trip.event.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TripEventTypeName {

    ACCOMMODATION("Accommodation"),
    TRANSFER("Transfer"),
    TOUR("Tour");

    @JsonValue
    private final String displayValue;

    TripEventTypeName(String displayValue) {
        this.displayValue = displayValue;
    }

    @JsonCreator
    public static TripEventTypeName forValue(String value) {
        return TripEventTypeName.valueOf(value.toUpperCase());
    }
}
