package com.mytrip.demo.application.port.out;

import com.mytrip.demo.application.persistance.trip.model.event.TripEventJpa;

public interface EventAdditionPropsServiceImpl {
    void addAdditionalPropertiesToEvent(TripEventJpa eventCreated);
}
