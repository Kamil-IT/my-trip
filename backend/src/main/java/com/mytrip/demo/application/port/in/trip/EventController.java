package com.mytrip.demo.application.port.in.trip;

import com.mytrip.demo.application.port.in.trip.model.create.CreateEventDto;
import com.mytrip.demo.application.port.in.trip.model.delete.RemoveParticipantDto;
import com.mytrip.demo.application.port.in.trip.model.update.AddAccommodationDto;
import com.mytrip.demo.application.port.in.trip.model.update.AddEventPropertyDto;
import com.mytrip.demo.application.port.in.trip.model.update.AddParticipantDto;
import com.mytrip.demo.application.port.in.trip.model.update.UpdateEventDto;
import com.mytrip.demo.domain.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.UUID;

public interface EventController {
    @PostMapping("/trip/event")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    Event addEvent(@RequestBody @Valid CreateEventDto event, Authentication authentication);

    @DeleteMapping("/trip/event/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    ResponseEntity<Void> deleteEvent(@PathVariable UUID id);

    @PostMapping("/trip/event/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    ResponseEntity<Void> updateEvent(@PathVariable UUID id, @Valid @RequestBody UpdateEventDto updateEventDto);

    @PostMapping("/trip/event/participant")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    ResponseEntity<Void> addEventParticipant(@RequestBody @Valid AddParticipantDto participant);

    @DeleteMapping("/trip/event/participant")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    ResponseEntity<Void> removeEventParticipant(@RequestBody @Valid RemoveParticipantDto participant);

    @PostMapping("/trip/event/property")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    ResponseEntity<Void> addEventProperty(@RequestBody @Valid AddEventPropertyDto property);

    @PostMapping("/trip/event/accommodation/property")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    ResponseEntity<Void> addAccommodationProperties(@RequestBody @Valid AddAccommodationDto property);

    @GetMapping("/trip/event")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    Event getEvent(@PathParam("id") UUID id);
}
