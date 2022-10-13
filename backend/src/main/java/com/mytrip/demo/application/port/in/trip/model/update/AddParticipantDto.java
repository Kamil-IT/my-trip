package com.mytrip.demo.application.port.in.trip.model.update;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AddParticipantDto {

    @Email
    String email;

    @NotNull
    UUID uuid;
}
