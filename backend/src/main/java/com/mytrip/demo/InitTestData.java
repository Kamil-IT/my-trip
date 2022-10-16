package com.mytrip.demo;

import com.mytrip.demo.application.port.in.user.model.CreateUser;
import com.mytrip.demo.application.port.out.UserService;
import com.mytrip.demo.application.port.out.impl.TripServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class InitTestData {

    private final UserService userService;
    private final TripServiceImpl tripServiceImpl;

    @PostConstruct
    void initData(){
        CreateUser user1 = CreateUser.builder()
                .email("email@gamil.com")
                .authority("ADMIN")
                .password("pass")
                .build();

        userService.create(user1);
    }
}
