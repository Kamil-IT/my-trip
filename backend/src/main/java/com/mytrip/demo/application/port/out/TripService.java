package com.mytrip.demo.application.port.out;

import com.mytrip.demo.application.persistance.trip.model.TripJpa;
import com.mytrip.demo.application.port.in.trip.model.create.CreateTripDto;
import com.mytrip.demo.application.port.in.trip.model.update.UpdateTripDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface TripService extends CRUDService<TripJpa, UUID, CreateTripDto, UpdateTripDto> {
    List<TripJpa> getAll(String email);

    TripJpa get(UUID id);

    @Override
    TripJpa create(CreateTripDto trip);

    @Override
    void update(UUID id, UpdateTripDto trip);

    @Transactional
    @Override
    void delete(UUID id);
}
