package com.mytrip.demo.application.port.out;

import com.mytrip.demo.application.exception.ResourceNotFoundException;
import com.mytrip.demo.application.persistance.trip.TripEventRepository;
import com.mytrip.demo.application.persistance.user.*;
import com.mytrip.demo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserEventParticipantRepository userEventParticipantRepository;
    private final UserTripParticipantRepository usertripParticipantRepository;

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

    public UserJpa createUser(User user) {
        String email = user.getEmail();
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        return userRepository.save(UserJpa.builder().email(email).password(encodedPassword).authority("USER").build());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserJpa userJpa = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Can not find user with email: " + email));

        return User.builder()
                .password(userJpa.getPassword())
                .email(userJpa.getEmail())
                .authorities(List.of(userJpa))
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .accountNonExpired(true)
                .enabled(true)
                .build();
    }
}
