package com.mytrip.demo.application.port.in.trip.model.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AddEventPropertyDto {

    @NotBlank
    String propertyKey;
    @NotBlank
    String propertyValue;

    @NotNull
    UUID eventUuid;
}
