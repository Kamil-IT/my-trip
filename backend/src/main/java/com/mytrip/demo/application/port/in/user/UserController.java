package com.mytrip.demo.application.port.in.user;

import com.mytrip.demo.application.port.in.user.mapper.UserMapper;
import com.mytrip.demo.application.port.in.user.model.UserDto;
import com.mytrip.demo.application.port.out.UserService;
import com.mytrip.demo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/v1")
public class UserController {

    private final UserMapper userMapper;

    @PostConstruct
    public void init(){
        userService.createUser(UserDto.builder().email("email@gamil.com").authority("ADMIN").password("pass").build());
    }

    private final UserService userService;


    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/user")
    public List<User> getUsers() {
        return userMapper.toDomain(userService.getAll());
    }


    @GetMapping("/user/current")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public User getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        return userMapper.toDomain(userService.getById(email));
    }

    @GetMapping("/user/{email}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public User getUser(@PathVariable("email") String email) {
        return userMapper.toDomain(userService.getById(email));
    }

    @PostMapping("/user")
    public User createUser(@Valid @RequestBody UserDto user) {
        return userMapper.toDomain(userService.createUser(user));
    }
}
