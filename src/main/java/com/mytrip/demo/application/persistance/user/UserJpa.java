package com.mytrip.demo.application.persistance.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@RequiredArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity(name = "user")
public class UserJpa {

    @Id
    private String email;
    private String password;
}
