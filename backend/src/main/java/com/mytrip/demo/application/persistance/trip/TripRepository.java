package com.mytrip.demo.application.persistance.trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TripRepository extends JpaRepository<TripJpa, Long> {

    Optional<TripJpa> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);
}
