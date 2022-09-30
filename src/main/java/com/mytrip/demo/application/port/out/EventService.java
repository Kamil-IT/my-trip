package com.mytrip.demo.application.port.out;

import com.mytrip.demo.application.exception.ResourceNotFoundException;
import com.mytrip.demo.application.persistance.trip.TripEventRepository;
import com.mytrip.demo.application.persistance.trip.TripJpa;
import com.mytrip.demo.application.persistance.trip.event.TripEventJpa;
import com.mytrip.demo.application.persistance.trip.event.type.TripEventTypeJpa;
import com.mytrip.demo.application.persistance.user.UserJpa;
import com.mytrip.demo.application.port.in.trip.model.CreateEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {

    private final TripEventRepository repository;

    private final UserService userService;
    private final TripService tripService;

    public TripEventJpa getEventById(UUID uuid) {
        return findByUuid(uuid);
    }

    public TripEventJpa create(CreateEventDto event, String userEmail) {
        UserJpa user = userService.getById(userEmail);
        TripJpa trip = tripService.getById(event.getEventId());

        TripEventJpa eventCreated = TripEventJpa.builder().title(event.getTitle()).latitude(event.getLatitude()).longitude(event.getLongitude()).startDate(event.getFrom()).endDate(event.getTo()).tripType(TripEventTypeJpa.builder().name(event.getType()).build()).build();
        trip.getTripEvents()
                .add(eventCreated);
        repository.save(eventCreated);
        return eventCreated;
    }

    public void addParticipants(UUID eventUuid, String email) {
        TripEventJpa event = findByUuid(eventUuid);
        UserJpa user = userService.getById(email);

        event.addParticipants(user);
    }

    public void addProperty(String key, String value, UUID eventUuid) {
        TripEventJpa event = findByUuid(eventUuid);
        event.addProperty(key, value);
    }

    private TripEventJpa findByUuid(UUID eventUuid) {
        return repository.findByUuid(eventUuid)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
