package com.mytrip.demo.application.port.in.user;

import com.mytrip.demo.application.persistance.user.UserJpa;
import com.mytrip.demo.application.port.out.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;


    @GetMapping("/user")
    public List<UserJpa> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/user")
    public UserJpa getUser(@PathParam("email") String email) {
        return userService.getById(email);
    }

    @PostMapping("/user")
    public UserJpa createUser(@RequestBody UserJpa userJpa) {
        return userService.createUser(userJpa);
    }
}
