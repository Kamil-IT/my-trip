package com.mytrip.demo.infrastructure.weather.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Weather16dayDescription {
    String icon;
    Integer code;
    String description;
}
