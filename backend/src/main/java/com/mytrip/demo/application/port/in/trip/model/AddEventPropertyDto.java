package com.mytrip.demo.application.port.in.trip.model;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
public class AddEventPropertyDto {

    @NotBlank
    String propertyKey;
    @NotBlank
    String propertyValue;

    @NotNull
    UUID eventUuid;
}
