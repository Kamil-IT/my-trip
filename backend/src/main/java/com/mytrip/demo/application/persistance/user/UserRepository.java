package com.mytrip.demo.application.persistance.user;

import com.mytrip.demo.application.persistance.user.model.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserJpa, String> {

    Optional<UserJpa> findByEmail(String email);
}
