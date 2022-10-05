package com.mytrip.demo.application.port.out;

import com.mytrip.demo.application.exception.ResourceNotFoundException;
import com.mytrip.demo.application.persistance.trip.TripEventRepository;
import com.mytrip.demo.application.persistance.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserEventParticipantRepository userEventParticipantRepository;
    private final UserTripParticipantRepository usertripParticipantRepository;

    public List<UserTripParticipantsJpa> getAllTripParticipant() {
        return usertripParticipantRepository.findAll();
    }

    public List<UserJpa> getAll() {
        return userRepository.findAll();
    }

    public UserJpa getById(String email) {
        return userRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
    }

    public UserEventParticipantsJpa getEventParticipantById(String email) {
        return userEventParticipantRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
    }

    public UserTripParticipantsJpa getTripParticipantById(String email) {
        return usertripParticipantRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
    }

    public UserJpa createUser(UserJpa user) {
        return userRepository.save(user);
    }
}
