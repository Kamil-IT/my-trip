package com.mytrip.demo.application.port.out;

import com.mytrip.demo.application.persistance.trip.TripJpa;
import com.mytrip.demo.application.persistance.user.UserJpa;
import com.mytrip.demo.application.port.in.trip.model.CreateTripDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TripService {
    private List<TripJpa> trips = new ArrayList<>();

    private final UserService userService;

    public List<TripJpa> getAll(){
        return trips;
    }

    public TripJpa getById(UUID id){
        return trips.stream().filter(trip -> trip.getUuid().equals(id)).findFirst().orElseThrow();
    }

    public TripJpa create(CreateTripDto trip, String email){
        TripJpa createdTrip = TripJpa.builder()
                .tripEvents(new ArrayList<>()).to(trip.getTo()).from(trip.getFrom()).participants(userService.getAll()).title(trip.getTitle()).build();

        trips.add(createdTrip);

        return createdTrip;
    }

    public void addParticipants(UUID tripUUID, String email) {
        TripJpa trip = trips.stream().filter(trp -> tripUUID.equals(trp.getUuid())).findFirst().orElseThrow();
        UserJpa user = userService.getById(email);
        trip.addParticipants(user);
    }
}
