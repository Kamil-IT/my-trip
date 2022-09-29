package com.mytrip.demo.application.persistance.trip.event.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@RequiredArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity(name = "trip_event_type")
public class TripEventTypeJpa {

    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private TripEventTypeName name;
}
