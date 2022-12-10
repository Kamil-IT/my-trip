package com.mytrip.demo.application.port.in.trip;

import com.mytrip.demo.application.port.in.trip.model.create.CreateTripDto;
import com.mytrip.demo.application.port.in.trip.model.delete.RemoveTripParticipantDto;
import com.mytrip.demo.application.port.in.trip.model.get.GetTripsDto;
import com.mytrip.demo.application.port.in.trip.model.update.AddParticipantDto;
import com.mytrip.demo.application.port.in.trip.model.update.UpdateTripDto;
import com.mytrip.demo.domain.Trip;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.UUID;

public interface TripController {
    @GetMapping("/trip/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    Trip getTrip(@PathVariable("id") UUID id);

    @GetMapping("/trip")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    GetTripsDto getTrips(Authentication authentication);

    @PostMapping("/trip")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    Trip addTrip(@RequestBody @Valid CreateTripDto trip, Authentication authentication);

    @PutMapping("/trip")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    ResponseEntity<Void> updateTrip(@RequestBody @Valid UpdateTripDto trip);

    @DeleteMapping("/trip/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    ResponseEntity<Void> updateTrip(@PathVariable UUID id);

    @PostMapping("/trip/participant")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    ResponseEntity<Void> addTripParticipant(@RequestBody @Valid AddParticipantDto participant);

    @DeleteMapping("/trip/participant")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    ResponseEntity<Void> removeTripParticipant(@RequestBody @Valid RemoveTripParticipantDto participant);
}
