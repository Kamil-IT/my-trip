package com.mytrip.demo;

import com.mytrip.demo.application.port.in.user.model.CreateUserDto;
import com.mytrip.demo.application.port.out.TripService;
import com.mytrip.demo.application.port.out.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class InitTestData {

    private final UserService userService;
    private final TripService tripService;

    @PostConstruct
    void initData() {
        CreateUserDto userAdmin1 = CreateUserDto.builder()
                .email("email@gamil.com")
                .authority("ADMIN")
                .password("pass")
                .build();
        CreateUserDto userAdmin2 = CreateUserDto.builder()
                .email("jan.kowalski@gamil.com")
                .authority("ADMIN")
                .password("pass")
                .build();
        CreateUserDto userAdmin3 = CreateUserDto.builder()
                .email("julia.dodge@gamil.com")
                .authority("ADMIN")
                .password("pass")
                .build();
        CreateUserDto userAdmin4 = CreateUserDto.builder()
                .email("julia.kamczarek@gamil.com")
                .authority("ADMIN")
                .password("pass")
                .build();

        userService.create(userAdmin1);
        userService.create(userAdmin2);
        userService.create(userAdmin3);
        userService.create(userAdmin4);
    }
}
