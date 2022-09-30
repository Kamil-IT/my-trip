package com.mytrip.demo.application.persistance.trip.event.type;

import com.mytrip.demo.application.persistance.trip.event.TripEventJpa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "tripType")
    private List<TripEventJpa> trips = new ArrayList<>();
}
