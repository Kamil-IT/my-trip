package com.mytrip.demo.application.port.in.trip.model;

import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
public class AddEventParticipantDto {

    @Email
    String email;

    @NotNull
    UUID eventUUID;
}
