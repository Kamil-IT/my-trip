package com.mytrip.demo.application.persistance.user;

import com.mytrip.demo.application.persistance.user.model.UserTripParticipantsJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTripParticipantRepository extends JpaRepository<UserTripParticipantsJpa, String> {

    Optional<UserTripParticipantsJpa> findByEmail(String email);
}
