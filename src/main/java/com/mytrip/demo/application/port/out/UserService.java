package com.mytrip.demo.application.port.out;

import com.mytrip.demo.application.persistance.user.UserJpa;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @PostConstruct
    public void initTestData() {
        users.add(UserJpa.builder().email("test@gmail.com").password("test").build());
    }

    private List<UserJpa> users = new ArrayList<>();

    public List<UserJpa> getAll(){
        return users;
    }

    public UserJpa getById(String email){
        return users.stream().filter(trip -> trip.getEmail().equals(email)).findFirst().orElseThrow();
    }

    public UserJpa createUser(UserJpa user){
        users.add(user);
        return user;
    }
}
