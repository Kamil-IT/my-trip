package com.mytrip.demo.application.persistance.user;

import com.mytrip.demo.application.persistance.user.model.UserEventParticipantsJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEventParticipantRepository extends JpaRepository<UserEventParticipantsJpa, String> {

    Optional<UserEventParticipantsJpa> findByEmail(String email);
}
