package com.mytrip.demo.application.persistance.user;

import com.mytrip.demo.application.persistance.trip.TripJpa;
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
@Entity(name = "user_app")
public class UserJpa {

    @Id
    private String email;
    private String password;

    @ManyToMany
    @JoinTable(name = "user_app_trip")
    private List<TripJpa> trips = new ArrayList<>();

    @OneToMany(mappedBy = "creator")
    private List<TripEventJpa> tripEventsCreated = new ArrayList<>();

    @ManyToMany(mappedBy = "participants")
    private List<TripEventJpa> tripEventsParticipants = new ArrayList<>();
}
