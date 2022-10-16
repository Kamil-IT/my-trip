package com.mytrip.demo.application.port.in.user;

import com.mytrip.demo.application.port.in.user.model.CreateUserDto;
import com.mytrip.demo.application.port.in.user.model.UpdateUserDto;
import com.mytrip.demo.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface UserController {
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/user")
    List<User> getUsers();

    @GetMapping("/user/current")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    User getCurrentUser(Authentication authentication);

    @GetMapping("/user/{email}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    User getUser(@PathVariable("email") String email);

    @PostMapping("/user")
    User createUser(@Valid @RequestBody CreateUserDto user);

    @PostMapping("/user/{email}")
    ResponseEntity<Void> update(@PathVariable String email, @Valid @RequestBody UpdateUserDto user);
}
