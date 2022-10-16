package com.mytrip.demo.application.port.out;

import com.mytrip.demo.application.persistance.user.model.UserEventParticipantsJpa;
import com.mytrip.demo.application.persistance.user.model.UserJpa;
import com.mytrip.demo.application.persistance.user.model.UserTripParticipantsJpa;
import com.mytrip.demo.application.port.in.user.model.CreateUserDto;
import com.mytrip.demo.application.port.in.user.model.UpdateUserDto;

import java.util.List;

public interface UserService extends CRUDService<UserJpa, String, CreateUserDto, UpdateUserDto> {
    List<UserJpa> getAll();

    UserEventParticipantsJpa getEventParticipantById(String email);

    UserTripParticipantsJpa getTripParticipantById(String email);
}
