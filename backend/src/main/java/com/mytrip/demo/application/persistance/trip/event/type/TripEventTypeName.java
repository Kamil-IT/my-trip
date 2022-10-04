package com.mytrip.demo.application.persistance.trip.event.type;

public enum TripEventTypeName {

    ACCOMMODATION("Accommodation"),
    TRANSFER("Transfer"),
    TOUR("Tour");


    private final String displayValue;

    TripEventTypeName(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
