package com.mytrip.demo.application.port.in.trip.impl;

import com.mytrip.demo.application.persistance.trip.model.TripJpa;
import com.mytrip.demo.application.port.in.trip.TripController;
import com.mytrip.demo.application.port.in.trip.mapper.TripMapper;
import com.mytrip.demo.application.port.in.trip.model.create.CreateTripDto;
import com.mytrip.demo.application.port.in.trip.model.delete.RemoveTripParticipantDto;
import com.mytrip.demo.application.port.in.trip.model.get.GetTripsDto;
import com.mytrip.demo.application.port.in.trip.model.update.AddParticipantDto;
import com.mytrip.demo.application.port.in.trip.model.update.UpdateTripDto;
import com.mytrip.demo.application.port.out.ParticitableService;
import com.mytrip.demo.application.port.out.TripService;
import com.mytrip.demo.domain.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
class TripControllerImpl implements TripController {

    private final TripMapper tripMapper;
    @Qualifier("tripServiceImpl")
    private final ParticitableService particitableService;
    @Qualifier("tripServiceImpl")
    private final TripService tripService;

    @Override
    @GetMapping("/trip/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public Trip getTrip(@PathVariable("id") UUID id) {
        return tripMapper.toDomain(tripService.get(id));
    }

    @Override
    @GetMapping("/trip")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public GetTripsDto getTrips(Authentication authentication) {
        String email = authentication.getName();
        List<TripJpa> all = tripService.getAll(email);
        return new GetTripsDto(tripMapper.toDomain(all));
    }

    @Override
    @PostMapping("/trip")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Trip addTrip(@RequestBody @Valid CreateTripDto trip, Authentication authentication) {
        String email = authentication.getName();
        trip.setCreator(trip.getCreator() == null ? email : trip.getCreator());
        return tripMapper.toDomain(tripService.create(trip));
    }

    @Override
    @PutMapping("/trip")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> updateTrip(@RequestBody @Valid UpdateTripDto trip) {
        tripService.update(trip.getUuid(), trip);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/trip/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> updateTrip(@PathVariable UUID id) {
        tripService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PostMapping("/trip/participant")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Void> addTripParticipant(@RequestBody @Valid AddParticipantDto participant) {
        particitableService.addParticipant(participant.getUuid(), participant.getEmail());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/trip/participant")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Void> removeTripParticipant(@RequestBody @Valid RemoveTripParticipantDto participant) {
        particitableService.removeParticipant(participant.getUuid(), participant.getEmail());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
