package com.mytrip.demo.application.port.out;

import com.mytrip.demo.application.exception.ResourceNotFoundException;
import com.mytrip.demo.application.persistance.user.*;
import com.mytrip.demo.application.port.in.user.model.UserDto;
import com.mytrip.demo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    public UserJpa createUser(UserDto user) {
        String email = user.getEmail();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        String grantedAuthority = user.getAuthority();

        UserJpa userToSave = UserJpa.builder()
                .email(email)
                .password(encodedPassword)
                .authority(grantedAuthority)
                .build();

        return userRepository.save(userToSave);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserJpa userJpa = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Can not find user with email: " + email));

        return User.builder()
                .password(userJpa.getPassword())
                .email(userJpa.getEmail())
                .authorities(List.of(User.Authority.builder().authority(userJpa.getAuthority()).build()))
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .accountNonExpired(true)
                .enabled(true)
                .build();
    }
}
