package com.mytrip.demo.application.port.in.trip;

import com.mytrip.demo.application.persistance.trip.TripJpa;
import com.mytrip.demo.application.persistance.trip.event.TripEventJpa;
import com.mytrip.demo.application.port.in.trip.mapper.EventMapper;
import com.mytrip.demo.application.port.in.trip.mapper.TripMapper;
import com.mytrip.demo.application.port.in.trip.model.*;
import com.mytrip.demo.domain.Trip;
import com.mytrip.demo.application.port.out.EventService;
import com.mytrip.demo.application.port.out.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class TripController {

    private final TripService tripService;
    private final TripMapper tripMapper;
    private final EventService eventService;

//    Trip

    @GetMapping("/trip/{id}")
    public TripJpa getTrip(@PathVariable("id") UUID id) {
        return tripService.getById(id);
    }

    @GetMapping("/trip")
    public GetTripsDto getTrips() {
        List<TripJpa> all = tripService.getAll();
        return new GetTripsDto(tripMapper.toDomain(all));
    }

    @PostMapping("/trip")
    public TripJpa addTrip(@RequestBody @Valid CreateTripDto trip) {
//        From spring security get user
        return tripService.createOrUpdate(trip, "email@gamil.com");
    }

    @PostMapping("/trip/participant")
    public ResponseEntity<Void> addTripParticipant(@Valid AddEventParticipantDto participant) {
//        From spring security get user
        tripService.addParticipants(participant.getEventUUID(), participant.getEmail());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/trip/participant")
    public ResponseEntity<Void> removeTripParticipant(@Valid RemoveTripParticipantDto participant) {
//        From spring security get user
        tripService.deleteParticipant(participant.getTripUUID(), participant.getEmail());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    Event

    @PostMapping("/trip/event")
    public TripEventJpa addEvent(@Valid CreateEventDto event) {
//        From spring security get user
        return eventService.create(event, "email");
    }

    @PostMapping("/trip/event/participant")
    public ResponseEntity<Void> addEventParticipant(@Valid AddEventParticipantDto participant) {
//        From spring security get user
        eventService.addParticipants(participant.getEventUUID(), participant.getEmail());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/trip/event/property")
    public ResponseEntity<Void> addEventProperty(@Valid AddEventPropertyDto property) {
//        From spring security get user
        eventService.addProperty(property.getPropertyKey(), property.getPropertyValue(), property.getEventUuid());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/trip/event")
    public TripEventJpa getEvent(@PathParam("id") UUID id) {
        return eventService.getEventById(id);
    }
}
