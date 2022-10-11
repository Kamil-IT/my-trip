package com.mytrip.demo.application.port.out;

import com.mytrip.demo.application.exception.ResourceNotFoundException;
import com.mytrip.demo.application.persistance.trip.TripJpa;
import com.mytrip.demo.application.persistance.trip.TripRepository;
import com.mytrip.demo.application.persistance.user.UserEventParticipantsJpa;
import com.mytrip.demo.application.persistance.user.UserJpa;
import com.mytrip.demo.application.persistance.user.UserTripParticipantsJpa;
import com.mytrip.demo.application.port.in.trip.model.CreateTripDto;
import com.mytrip.demo.application.port.in.trip.model.UpdateTripDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

    public TripJpa create(CreateTripDto trip, String email){
        UserJpa user = userService.getById(email);
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

    public TripJpa update(UpdateTripDto trip){
        TripJpa tripDb = repository.findByUuid(trip.getUuid()).orElseThrow(ResourceNotFoundException::new);

        tripDb.setEndDate(trip.getTo());
        tripDb.setStartDate(trip.getTo());
        tripDb.setTitle(trip.getTitle());

        return repository.save(tripDb);
    }

    public void addParticipants(UUID tripUUID, String email) {
        TripJpa trip = getByUuid(tripUUID);
        UserTripParticipantsJpa user = userService.getTripParticipantById(email);
        trip.addParticipants(user);
        repository.save(trip);
    }

    public void deleteParticipant(UUID tripUUID, String email) {
        TripJpa trip = getByUuid(tripUUID);
        UserTripParticipantsJpa user = userService.getTripParticipantById(email);

        trip.removeParticipants(user);
        repository.save(trip);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteByUuid(id);
    }
}
