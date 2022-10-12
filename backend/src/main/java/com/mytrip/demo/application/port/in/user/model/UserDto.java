package com.mytrip.demo.application.port.in.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserDto {

    private String email;
    private String password;
}
