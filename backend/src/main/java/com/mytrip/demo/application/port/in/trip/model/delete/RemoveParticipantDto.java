package com.mytrip.demo.application.port.in.trip.model.delete;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class RemoveParticipantDto {

    @Email
    String email;

    @NotNull
    UUID uuid;
}
