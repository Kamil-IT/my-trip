package com.mytrip.demo.application.port.in.user;

import com.mytrip.demo.application.persistance.user.UserJpa;
import com.mytrip.demo.application.port.out.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.websocket.server.PathParam;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class UserController {

    @PostConstruct
    public void init(){
        userService.createUser(UserJpa.builder().email("email@gamil.com").password("pass").build());
    }

    private final UserService userService;


    @GetMapping("/user")
    public List<UserJpa> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/user/{email}")
    public UserJpa getUser(@PathVariable("email") String email) {
        return userService.getById(email);
    }

    @PostMapping("/user")
    public UserJpa createUser(@RequestBody UserJpa userJpa) {
        return userService.createUser(userJpa);
    }
}
