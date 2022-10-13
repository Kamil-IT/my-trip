package com.mytrip.demo.application.port.out;

import com.mytrip.demo.application.exception.ResourceNotFoundException;
import com.mytrip.demo.application.persistance.trip.TripEventRepository;
import com.mytrip.demo.application.persistance.trip.TripJpa;
import com.mytrip.demo.application.persistance.trip.TripRepository;
import com.mytrip.demo.application.persistance.trip.event.TripEventJpa;
import com.mytrip.demo.application.persistance.user.UserEventParticipantsJpa;
import com.mytrip.demo.application.port.in.trip.model.CreateEventDto;
import com.mytrip.demo.application.port.in.trip.model.UpdateEventDto;
import com.mytrip.demo.infrastructure.geocoding.GeocodingClient;
import com.mytrip.demo.infrastructure.geocoding.model.GeocodingForwardRequest;
import com.mytrip.demo.infrastructure.geocoding.model.GeocodingForwardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EventService {

    private final TripEventRepository repository;
    private final TripRepository repositoryTrip;
    private final GeocodingClient geocodingClient;
    private final EventAdditionPropsService eventAdditionPropsService;

    private final UserService userService;
    private final TripService tripService;

    public TripEventJpa getEventById(UUID uuid) {
        return findByUuid(uuid);
    }

    public TripEventJpa create(CreateEventDto event, String userEmail) {
        UserEventParticipantsJpa user = userService.getEventParticipantById(userEmail);
        TripJpa trip = tripService.getById(event.getTripId());

        String locationDescription = event.getLocationDescription();
        GeocodingForwardResponse coordinates = geocodingClient.getCoordinates(GeocodingForwardRequest.builder().city(locationDescription).build());

        TripEventJpa eventCreated = TripEventJpa.builder()
                .uuid(UUID.randomUUID())
                .creator(userEmail)
                .participants(new HashSet<>())
                .title(event.getTitle())
                .latitude(Double.valueOf(coordinates.getLon()))
                .longitude(Double.valueOf(coordinates.getLat()))
                .locationDescription(coordinates.getDisplay_name())
                .startDate(event.getFrom())
                .endDate(event.getTo())
                .tripType(event.getType())
                .build();

        eventAdditionPropsService.addAdditionalPropertiesToEvent(eventCreated);

        eventCreated.setTrip(trip);
        eventCreated.getTrip().addEvent(eventCreated);
        eventCreated.addParticipants(user);
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

    @Transactional
    public void delete(UUID id) {
        repository.deleteByUuid(id);
    }

    public void update(UUID id, UpdateEventDto updateEventDto) {
        TripEventJpa event = findByUuid(id);
        event.setStartDate(updateEventDto.getFrom());
        event.setEndDate(updateEventDto.getTo());
        event.setTitle(updateEventDto.getTitle());
        event.setLocationDescription(updateEventDto.getLocationDescription());
        repository.save(event);
    }
}
