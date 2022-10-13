package com.mytrip.demo.application.persistance.trip;

import com.mytrip.demo.application.persistance.trip.model.event.TripEventJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TripEventRepository extends JpaRepository<TripEventJpa, Long> {

    Optional<TripEventJpa> findByUuid(UUID uuid);
    void deleteByUuid(UUID uuid);
}
