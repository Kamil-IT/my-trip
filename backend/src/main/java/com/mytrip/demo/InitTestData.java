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
    void initData(){
        CreateUserDto user1 = CreateUserDto.builder()
                .email("email@gamil.com")
                .authority("ADMIN")
                .password("pass")
                .build();

        userService.create(user1);
    }
}
