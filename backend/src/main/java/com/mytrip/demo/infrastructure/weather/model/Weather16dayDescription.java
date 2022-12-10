package com.mytrip.demo.infrastructure.weather.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather16dayDescription {
    String icon;
    Integer code;
    String description;
}
