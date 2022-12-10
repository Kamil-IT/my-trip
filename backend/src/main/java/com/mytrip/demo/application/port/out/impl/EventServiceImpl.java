package com.mytrip.demo.application.port.out.impl;

import com.mytrip.demo.application.exception.ResourceNotFoundException;
import com.mytrip.demo.application.persistance.trip.TripEventRepository;
import com.mytrip.demo.application.persistance.trip.model.TripJpa;
import com.mytrip.demo.application.persistance.trip.model.event.TripEventJpa;
import com.mytrip.demo.application.persistance.user.model.UserEventParticipantsJpa;
import com.mytrip.demo.application.port.in.trip.model.create.CreateEventDto;
import com.mytrip.demo.application.port.in.trip.model.update.AddAccommodationDto;
import com.mytrip.demo.application.port.in.trip.model.update.UpdateEventDto;
import com.mytrip.demo.application.port.out.EventService;
import com.mytrip.demo.application.port.out.ParticitableService;
import com.mytrip.demo.application.port.out.UserService;
import com.mytrip.demo.infrastructure.geocoding.GeocodingClient;
import com.mytrip.demo.infrastructure.geocoding.model.GeocodingForwardRequest;
import com.mytrip.demo.infrastructure.geocoding.model.GeocodingForwardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class EventServiceImpl implements ParticitableService, EventService {

    private final TripEventRepository repository;
    private final GeocodingClient geocodingClient;
    private final EventAdditionPropsService eventAdditionPropsService;
    private final UserService userService;
    private final TripServiceImpl tripServiceImpl;

    @Override
    public TripEventJpa get(UUID uuid) {
        return findByUuid(uuid);
    }

    @Override
    public TripEventJpa create(CreateEventDto event) {
        String email = event.getCreator();
        UserEventParticipantsJpa user = userService.getEventParticipantById(email);
        TripJpa trip = tripServiceImpl.get(event.getTripId());

        String locationDescription = event.getLocationDescription();
        GeocodingForwardResponse coordinates = geocodingClient.getCoordinates(GeocodingForwardRequest.builder().city(locationDescription).build());

        TripEventJpa eventCreated = TripEventJpa.builder()
                .uuid(UUID.randomUUID())
                .creator(email)
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

    @Override
    public void addParticipant(UUID eventUuid, String email) {
        TripEventJpa event = findByUuid(eventUuid);
        UserEventParticipantsJpa user = userService.getEventParticipantById(email);

        event.addParticipants(user);
        repository.save(event);
    }

    @Override
    public void addProperty(String key, String value, UUID eventUuid) {
        TripEventJpa event = findByUuid(eventUuid);
        event.addProperty(key, value);
        repository.save(event);
    }

    @Override
    public void addAccommodation(AddAccommodationDto property) {
        addProperty("hotelPhotoUrl", property.getPhotoUrl(), property.getEventUuid());
        addProperty("hotelRating", property.getHotelRating(), property.getEventUuid());
        addProperty("hotelName", property.getHotelName(), property.getEventUuid());
        addProperty("hotelAddress", property.getAddress(), property.getEventUuid());
    }

    private TripEventJpa findByUuid(UUID eventUuid) {
        return repository.findByUuid(eventUuid)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void removeParticipant(UUID eventUuid, String email) {
        TripEventJpa event = findByUuid(eventUuid);
        UserEventParticipantsJpa user = userService.getEventParticipantById(email);

        event.removeParticipants(user);
        repository.save(event);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        repository.deleteByUuid(id);
    }

    @Override
    public void update(UUID id, UpdateEventDto updateEventDto) {
        TripEventJpa event = findByUuid(id);
        event.setStartDate(updateEventDto.getFrom());
        event.setEndDate(updateEventDto.getTo());
        event.setTitle(updateEventDto.getTitle());
        event.setLocationDescription(updateEventDto.getLocationDescription());
        repository.save(event);
    }
}
