package com.mytrip.demo.application.port.in.user.mapper;

import com.mytrip.demo.application.persistance.user.UserJpa;
import com.mytrip.demo.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "authorities", source = "authority")
    User toDomain(UserJpa user);

    List<User> toDomain(Collection<UserJpa> user);

    default Collection<User.Authority> toDomain(String authority) {
        return List.of(User.Authority.builder().authority(authority).build());
    }
}
