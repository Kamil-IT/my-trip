package com.mytrip.demo.application.port.in.trip;

import com.mytrip.demo.application.persistance.trip.TripJpa;
import com.mytrip.demo.application.persistance.trip.event.TripEventJpa;
import com.mytrip.demo.application.port.in.trip.mapper.EventMapper;
import com.mytrip.demo.application.port.in.trip.mapper.TripMapper;
import com.mytrip.demo.application.port.in.trip.model.*;
import com.mytrip.demo.application.port.out.EventService;
import com.mytrip.demo.application.port.out.TripService;
import com.mytrip.demo.domain.Event;
import com.mytrip.demo.domain.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class TripController {

    private final TripService tripService;
    private final TripMapper tripMapper;
    private final EventMapper eventMapper;
    private final EventService eventService;

//    Trip

    @GetMapping("/trip/{id}")
    public Trip getTrip(@PathVariable("id") UUID id) {
        return tripMapper.toDomain(tripService.getById(id));
    }

    @GetMapping("/trip")
    public GetTripsDto getTrips() {
        List<TripJpa> all = tripService.getAll();
        return new GetTripsDto(tripMapper.toDomain(all));
    }

    @PostMapping("/trip")
    public Trip addTrip(@RequestBody @Valid CreateTripDto trip) {
//        From spring security get user
        return tripMapper.toDomain(tripService.create(trip, "email@gamil.com"));
    }

    @PutMapping("/trip")
    public Trip updateTrip(@RequestBody @Valid UpdateTripDto trip) {
//        From spring security get user
        return tripMapper.toDomain(tripService.update(trip));
    }

    @PostMapping("/trip/participant")
    public ResponseEntity<Void> addTripParticipant(@RequestBody @Valid AddParticipantDto participant) {
//        From spring security get user
        tripService.addParticipants(participant.getUuid(), participant.getEmail());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/trip/participant")
    public ResponseEntity<Void> removeTripParticipant(@RequestBody @Valid RemoveTripParticipantDto participant) {
//        From spring security get user
        tripService.deleteParticipant(participant.getUuid(), participant.getEmail());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    Event

    @PostMapping("/trip/event")
    public Event addEvent(@RequestBody @Valid CreateEventDto event) {
//        From spring security get user
        return eventMapper.toDomain(eventService.create(event, "email@gamil.com"));
    }

    @PostMapping("/trip/event/participant")
    public ResponseEntity<Void> addEventParticipant(@RequestBody @Valid AddParticipantDto participant) {
//        From spring security get user
        eventService.addParticipants(participant.getUuid(), participant.getEmail());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/trip/event/property")
    public ResponseEntity<Void> addEventProperty(@RequestBody @Valid AddEventPropertyDto property) {
//        From spring security get user
        eventService.addProperty(property.getPropertyKey(), property.getPropertyValue(), property.getEventUuid());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/trip/event")
    public Event getEvent(@PathParam("id") UUID id) {
        return tripMapper.toDomain(eventService.getEventById(id));
    }
}
