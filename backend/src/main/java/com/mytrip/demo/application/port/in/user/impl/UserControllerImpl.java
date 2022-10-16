package com.mytrip.demo.application.port.in.user.impl;

import com.mytrip.demo.application.port.in.user.mapper.UserMapper;
import com.mytrip.demo.application.port.in.user.model.CreateUserDto;
import com.mytrip.demo.application.port.in.user.model.UpdateUserDto;
import com.mytrip.demo.application.port.out.UserService;
import com.mytrip.demo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/v1")
class UserControllerImpl implements com.mytrip.demo.application.port.in.user.UserController {

    private final UserMapper userMapper;
    private final UserService userService;


    @Override
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/user")
    public List<User> getUsers() {
        return userMapper.toDomain(userService.getAll());
    }


    @Override
    @GetMapping("/user/current")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public User getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        return userMapper.toDomain(userService.get(email));
    }

    @Override
    @GetMapping("/user/{email}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public User getUser(@PathVariable("email") String email) {
        return userMapper.toDomain(userService.get(email));
    }

    @Override
    @PostMapping("/user")
    public User createUser(@Valid @RequestBody CreateUserDto user) {
        return userMapper.toDomain(userService.create(user));
    }

    @Override
    @PostMapping("/user/{email}")
    public ResponseEntity<Void> update(@PathVariable String email, @Valid @RequestBody UpdateUserDto user) {
        userService.update(email, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
