package com.mytrip.demo.application.port.out;

import com.mytrip.demo.application.persistance.trip.model.event.TripEventJpa;
import com.mytrip.demo.application.port.in.trip.model.create.CreateEventDto;
import com.mytrip.demo.application.port.in.trip.model.update.AddAccommodationDto;
import com.mytrip.demo.application.port.in.trip.model.update.UpdateEventDto;
import com.mytrip.demo.application.port.out.CRUDService;

import javax.transaction.Transactional;
import java.util.UUID;

public interface EventService extends CRUDService<TripEventJpa, UUID, CreateEventDto, UpdateEventDto> {

    void addProperty(String key, String value, UUID eventUuid);

    void addAccommodation(AddAccommodationDto property);
}
