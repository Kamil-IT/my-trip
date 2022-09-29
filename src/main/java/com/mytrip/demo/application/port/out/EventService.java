package com.mytrip.demo.application.port.out;

import com.mytrip.demo.application.persistance.trip.TripJpa;
import com.mytrip.demo.application.persistance.trip.event.TripEventJpa;
import com.mytrip.demo.application.persistance.trip.event.type.TripEventTypeJpa;
import com.mytrip.demo.application.persistance.user.UserJpa;
import com.mytrip.demo.application.port.in.trip.model.CreateEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {

    private List<TripEventJpa> events;

    private final UserService userService;
    private final TripService tripService;

    public TripEventJpa getEventById(UUID uuid) {
        return events.stream().filter(event -> event.getUuid().equals(uuid)).findFirst().orElseThrow();
    }

    public TripEventJpa create(CreateEventDto event, String userEmail) {
        UserJpa user = userService.getById(userEmail);
        TripJpa trip = tripService.getById(event.getEventId());

        TripEventJpa eventCreated = TripEventJpa.builder().title(event.getTitle()).latitude(event.getLatitude()).longitude(event.getLongitude()).from(event.getFrom()).to(event.getTo()).tripEventTypeJpa(TripEventTypeJpa.builder().name(event.getType()).build()).build();
        trip.getTripEvents()
                .add(eventCreated);
        events.add(eventCreated);
        return eventCreated;
    }

    public void addParticipants(UUID eventUuid, String email) {
        TripEventJpa event = events.stream().filter(evn -> eventUuid.equals(evn.getUuid())).findFirst().orElseThrow();
        UserJpa user = userService.getById(email);

        event.addParticipants(user);
    }

    public void addProperty(String key, String value, UUID eventUuid) {
        TripEventJpa event = events.stream().filter(evn -> eventUuid.equals(evn.getUuid())).findFirst().orElseThrow();
        event.addProperty(key, value);
    }
}
