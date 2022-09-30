package com.mytrip.demo.application.persistance.trip.event.properties;

import com.mytrip.demo.application.persistance.trip.event.TripEventJpa;
import com.mytrip.demo.application.persistance.trip.event.type.TripEventTypeJpa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity(name = "trip_event_type_properties")
public class TripEventTypePropertiesJpa {

    @Id
    private String propertyKey;
    private String propertyValue;

    @ManyToOne
    private TripEventJpa tripEventJpa;
}
