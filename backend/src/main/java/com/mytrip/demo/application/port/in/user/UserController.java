package com.mytrip.demo.application.port.in.user;

import com.mytrip.demo.application.port.in.user.mapper.UserMapper;
import com.mytrip.demo.application.port.in.user.model.CreateUser;
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
public class UserController {

    private final UserMapper userMapper;
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
        return userMapper.toDomain(userService.get(email));
    }

    @GetMapping("/user/{email}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public User getUser(@PathVariable("email") String email) {
        return userMapper.toDomain(userService.get(email));
    }

    @PostMapping("/user")
    public User createUser(@Valid @RequestBody CreateUser user) {
        return userMapper.toDomain(userService.create(user));
    }

    @PostMapping("/user/{email}")
    public ResponseEntity<Void> update(@PathVariable String email, @Valid @RequestBody UpdateUserDto user) {
        userService.update(email, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
