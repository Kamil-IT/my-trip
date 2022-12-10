package com.mytrip.demo.application.port.in.trip.model.update;

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
public class AddTripParticipantDto {

    @Email
    String email;

    @NotNull
    UUID eventUUID;
}
