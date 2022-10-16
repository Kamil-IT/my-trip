package com.mytrip.demo.application.port.out.impl;

import com.mytrip.demo.application.exception.ResourceNotFoundException;
import com.mytrip.demo.application.persistance.trip.TripRepository;
import com.mytrip.demo.application.persistance.trip.model.TripJpa;
import com.mytrip.demo.application.persistance.user.model.UserJpa;
import com.mytrip.demo.application.persistance.user.model.UserTripParticipantsJpa;
import com.mytrip.demo.application.port.in.trip.model.create.CreateTripDto;
import com.mytrip.demo.application.port.in.trip.model.update.UpdateTripDto;
import com.mytrip.demo.application.port.out.ParticitableService;
import com.mytrip.demo.application.port.out.TripService;
import com.mytrip.demo.application.port.out.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TripServiceImpl implements ParticitableService, TripService {
    private final TripRepository repository;
    private final UserService userService;

    @Override
    public List<TripJpa> getAll(String email){
        return repository.findAll().stream()
                .filter(trip -> trip.getParticipants().stream().anyMatch(user -> user.getEmail().equals(email)))
                .collect(Collectors.toList());
    }

    @Override
    public TripJpa get(UUID id){
        return getByUuid(id);
    }

    private TripJpa getByUuid(UUID id) {
        return repository.findByUuid(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public TripJpa create(CreateTripDto trip){
        String email = trip.getCreator();
        UserJpa user = userService.get(email);
        UserTripParticipantsJpa participant = userService.getTripParticipantById(email);

        TripJpa createdTrip = TripJpa.builder()
                .endDate(trip.getTo())
                .startDate(trip.getFrom())
                .participants(Arrays.asList(participant))
                .title(trip.getTitle())
                .uuid(UUID.randomUUID())
                .build();
        createdTrip.setCreator(user.getEmail());

        repository.save(createdTrip);

        return createdTrip;
    }

    @Override
    public void update(UUID id, UpdateTripDto trip){
        TripJpa tripDb = repository.findByUuid(id).orElseThrow(ResourceNotFoundException::new);

        tripDb.setEndDate(trip.getTo());
        tripDb.setStartDate(trip.getTo());
        tripDb.setTitle(trip.getTitle());

        repository.save(tripDb);
    }

    @Override
    public void addParticipant(UUID tripUUID, String email) {
        TripJpa trip = getByUuid(tripUUID);
        UserTripParticipantsJpa user = userService.getTripParticipantById(email);
        trip.addParticipants(user);
        repository.save(trip);
    }

    @Override
    public void removeParticipant(UUID tripUUID, String email) {
        TripJpa trip = getByUuid(tripUUID);
        UserTripParticipantsJpa user = userService.getTripParticipantById(email);

        trip.removeParticipants(user);
        repository.save(trip);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        repository.deleteByUuid(id);
    }
}
