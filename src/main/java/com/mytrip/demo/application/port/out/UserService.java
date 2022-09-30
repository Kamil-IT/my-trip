package com.mytrip.demo.application.port.out;

import com.mytrip.demo.application.persistance.user.UserJpa;
import com.mytrip.demo.application.persistance.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<UserJpa> getAll() {
        return repository.findAll();
    }

    public UserJpa getById(String email) {
        return repository.findByEmail(email).orElseThrow(ReadOnlyFileSystemException::new);
    }

    public UserJpa createUser(UserJpa user) {
        return repository.save(user);
    }
}
