package com.mytrip.demo.application.port.in.trip;

import com.mytrip.demo.application.port.in.trip.mapper.TripMapper;
import com.mytrip.demo.application.port.in.trip.model.create.CreateEventDto;
import com.mytrip.demo.application.port.in.trip.model.delete.RemoveParticipantDto;
import com.mytrip.demo.application.port.in.trip.model.update.AddEventPropertyDto;
import com.mytrip.demo.application.port.in.trip.model.update.AddParticipantDto;
import com.mytrip.demo.application.port.in.trip.model.update.UpdateEventDto;
import com.mytrip.demo.application.port.out.EventService;
import com.mytrip.demo.domain.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.UUID;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class EventController {
    private final TripMapper tripMapper;
    private final EventService eventService;

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
