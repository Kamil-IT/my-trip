package com.mytrip.demo.application.port.out.impl;

import com.mytrip.demo.application.exception.ResourceNotFoundException;
import com.mytrip.demo.application.persistance.user.UserEventParticipantRepository;
import com.mytrip.demo.application.persistance.user.UserRepository;
import com.mytrip.demo.application.persistance.user.UserTripParticipantRepository;
import com.mytrip.demo.application.persistance.user.model.UserEventParticipantsJpa;
import com.mytrip.demo.application.persistance.user.model.UserJpa;
import com.mytrip.demo.application.persistance.user.model.UserTripParticipantsJpa;
import com.mytrip.demo.application.port.in.user.model.CreateUserDto;
import com.mytrip.demo.application.port.in.user.model.UpdateUserDto;
import com.mytrip.demo.application.port.out.UserService;
import com.mytrip.demo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserEventParticipantRepository userEventParticipantRepository;
    private final UserTripParticipantRepository usertripParticipantRepository;

    @Override
    public List<UserJpa> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserJpa get(String email) {
        return userRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public UserEventParticipantsJpa getEventParticipantById(String email) {
        return userEventParticipantRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public UserTripParticipantsJpa getTripParticipantById(String email) {
        return usertripParticipantRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public UserJpa create(CreateUserDto user) {
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
    public void delete(String email) {
        userRepository.deleteById(email);
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

    @Override
    public void update(String email, UpdateUserDto user) {
        UserJpa userJpa = get(email);
        userJpa.setPassword(user.getPassword() != null ? user.getPassword() : userJpa.getPassword());
        userJpa.setAuthority(user.getAuthority() != null ? user.getAuthority() : userJpa.getAuthority());
        userRepository.save(userJpa);
    }
}
