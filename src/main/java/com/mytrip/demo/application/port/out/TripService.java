package com.mytrip.demo.application.port.out;

import com.mytrip.demo.application.exception.ResourceNotFoundException;
import com.mytrip.demo.application.persistance.trip.TripJpa;
import com.mytrip.demo.application.persistance.trip.TripRepository;
import com.mytrip.demo.application.persistance.user.UserJpa;
import com.mytrip.demo.application.port.in.trip.model.CreateTripDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TripService {
    private final TripRepository repository;

    private final UserService userService;

    public List<TripJpa> getAll(){
        return repository.findAll();
    }

    public TripJpa getById(UUID id){
        return getByUuid(id);
    }

    private TripJpa getByUuid(UUID id) {
        return repository.findByUuid(id).orElseThrow(ResourceNotFoundException::new);
    }

    public TripJpa createOrUpdate(CreateTripDto trip, String email){
        UserJpa user = userService.getById(email);

        TripJpa createdTrip = TripJpa.builder()
                .endDate(trip.getTo())
                .startDate(trip.getFrom())
                .participants(userService.getAll())
                .title(trip.getTitle())
                .uuid(UUID.randomUUID())
                .build();
        createdTrip.setCreator(user.getEmail());

        repository.save(createdTrip);

        return createdTrip;
    }

    public void addParticipants(UUID tripUUID, String email) {
        TripJpa trip = getByUuid(tripUUID);
        UserJpa user = userService.getById(email);
        trip.addParticipants(user);
    }

    public void deleteParticipant(UUID tripUUID, String email) {
        TripJpa trip = getByUuid(tripUUID);
        UserJpa user = userService.getById(email);

        trip.removeParticipants(user);
    }
}
