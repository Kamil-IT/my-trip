package com.mytrip.demo.application.port.out.impl;

import com.mytrip.demo.application.persistance.trip.model.event.TripEventJpa;
import com.mytrip.demo.application.port.in.trip.model.create.CreateEventDto;
import com.mytrip.demo.application.port.in.trip.model.update.AddAccommodationDto;
import com.mytrip.demo.application.port.in.trip.model.update.UpdateEventDto;
import com.mytrip.demo.application.port.out.CRUDService;

import javax.transaction.Transactional;
import java.util.UUID;

public interface EventService extends CRUDService<TripEventJpa, UUID, CreateEventDto, UpdateEventDto> {
    @Override
    TripEventJpa get(UUID uuid);

    @Override
    TripEventJpa create(CreateEventDto event);

    void addProperty(String key, String value, UUID eventUuid);

    void addAccommodation(AddAccommodationDto property);

    @Override
    @Transactional
    void delete(UUID id);

    @Override
    void update(UUID id, UpdateEventDto updateEventDto);
}
