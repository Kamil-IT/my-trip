package com.mytrip.demo.application.port.out;

import com.mytrip.demo.application.exception.ResourceNotFoundException;
import com.mytrip.demo.application.persistance.trip.TripEventRepository;
import com.mytrip.demo.application.persistance.trip.TripJpa;
import com.mytrip.demo.application.persistance.trip.TripRepository;
import com.mytrip.demo.application.persistance.trip.event.TripEventJpa;
import com.mytrip.demo.application.persistance.user.UserEventParticipantsJpa;
import com.mytrip.demo.application.persistance.user.UserJpa;
import com.mytrip.demo.application.persistance.user.UserTripParticipantsJpa;
import com.mytrip.demo.application.port.in.trip.model.CreateEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {

    private final TripEventRepository repository;
    private final TripRepository repositoryTrip;

    private final UserService userService;
    private final TripService tripService;

    public TripEventJpa getEventById(UUID uuid) {
        return findByUuid(uuid);
    }

    public TripEventJpa create(CreateEventDto event, String userEmail) {
        UserEventParticipantsJpa user = userService.getEventParticipantById(userEmail);
        TripJpa trip = tripService.getById(event.getTripId());

        TripEventJpa eventCreated = TripEventJpa.builder()
                .uuid(UUID.randomUUID())
                .creator(userEmail)
                .participants(new HashSet<>())
                .title(event.getTitle())
                .latitude(10d)
                .longitude(10d)
                .locationDescription(event.getLocationDescription())
                .startDate(event.getFrom())
                .endDate(event.getTo())
                .tripType(event.getType())
                .build();

        eventCreated.setTrip(trip);
        eventCreated.getTrip().addEvent(eventCreated);
        eventCreated.addParticipants(user);
        eventCreated.setProperties(new ArrayList<>());
//        trip.addEvent(eventCreated);
        repository.save(eventCreated);
        return eventCreated;
    }

    public void addParticipants(UUID eventUuid, String email) {
        TripEventJpa event = findByUuid(eventUuid);
        UserEventParticipantsJpa user = userService.getEventParticipantById(email);

        event.addParticipants(user);
        repository.save(event);
    }

    public void addProperty(String key, String value, UUID eventUuid) {
        TripEventJpa event = findByUuid(eventUuid);
        event.addProperty(key, value);
    }

    private TripEventJpa findByUuid(UUID eventUuid) {
        return repository.findByUuid(eventUuid)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public void deleteParticipant(UUID eventUuid, String email) {
        TripEventJpa event = findByUuid(eventUuid);
        UserEventParticipantsJpa user = userService.getEventParticipantById(email);

        event.removeParticipants(user);
        repository.save(event);
    }
}
