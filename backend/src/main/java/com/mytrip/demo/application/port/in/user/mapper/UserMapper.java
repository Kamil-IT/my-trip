package com.mytrip.demo.application.port.in.user.mapper;

import com.mytrip.demo.application.persistance.user.UserJpa;
import com.mytrip.demo.domain.User;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain(UserJpa user);

    List<User> toDomain(Collection<UserJpa> user);
}
