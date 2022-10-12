package com.mytrip.demo.application.port.in.user.model;

import com.mytrip.demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
@Builder
public class UserDto {

    private String email;
    private String password;
    private String authority;
}
