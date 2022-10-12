package com.mytrip.demo.application.port.in.trip;

import com.mytrip.demo.application.persistance.trip.TripJpa;
import com.mytrip.demo.application.port.in.trip.mapper.TripMapper;
import com.mytrip.demo.application.port.in.trip.model.*;
import com.mytrip.demo.application.port.out.EventService;
import com.mytrip.demo.application.port.out.TripService;
import com.mytrip.demo.domain.Event;
import com.mytrip.demo.domain.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    private final EventService eventService;

//    Trip
    @GetMapping("/trip/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public Trip getTrip(@PathVariable("id") UUID id) {
        return tripMapper.toDomain(tripService.getById(id));
    }

    @GetMapping("/trip")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public GetTripsDto getTrips(Authentication authentication) {
        String email = authentication.getName();
        List<TripJpa> all = tripService.getAll(email);
        return new GetTripsDto(tripMapper.toDomain(all));
    }

    @PostMapping("/trip")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Trip addTrip(@RequestBody @Valid CreateTripDto trip, Authentication authentication) {
        String email = authentication.getName();
        return tripMapper.toDomain(tripService.create(trip, email));
    }

    @PutMapping("/trip")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Trip updateTrip(@RequestBody @Valid UpdateTripDto trip) {
        return tripMapper.toDomain(tripService.update(trip));
    }

    @DeleteMapping("/trip/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> updateTrip(@PathVariable UUID id) {
        tripService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/trip/participant")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Void> addTripParticipant(@RequestBody @Valid AddParticipantDto participant) {
        tripService.addParticipants(participant.getUuid(), participant.getEmail());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/trip/participant")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Void> removeTripParticipant(@RequestBody @Valid RemoveTripParticipantDto participant) {
        tripService.deleteParticipant(participant.getUuid(), participant.getEmail());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    Event

    @PostMapping("/trip/event")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public Event addEvent(@RequestBody @Valid CreateEventDto event, Authentication authentication) {
        String email = authentication.getName();
        return tripMapper.toDomain(eventService.create(event, email));
    }

    @DeleteMapping("/trip/event/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id) {
        eventService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/trip/event/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Void> updateEvent(@PathVariable UUID id, @Valid @RequestBody UpdateEventDto updateEventDto) {
        eventService.update(id, updateEventDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/trip/event/participant")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Void> addEventParticipant(@RequestBody @Valid AddParticipantDto participant) {
        eventService.addParticipants(participant.getUuid(), participant.getEmail());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/trip/event/participant")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Void> removeEventParticipant(@RequestBody @Valid RemoveParticipantDto participant) {
        eventService.deleteParticipant(participant.getUuid(), participant.getEmail());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/trip/event/property")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Void> addEventProperty(@RequestBody @Valid AddEventPropertyDto property) {
        eventService.addProperty(property.getPropertyKey(), property.getPropertyValue(), property.getEventUuid());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/trip/event")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public Event getEvent(@PathParam("id") UUID id) {
        return tripMapper.toDomain(eventService.getEventById(id));
    }
}
