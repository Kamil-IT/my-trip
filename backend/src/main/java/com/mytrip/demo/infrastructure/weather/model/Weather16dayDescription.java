package com.mytrip.demo.infrastructure.weather.model;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather16dayDescription {
    String icon;
    Integer code;
    String description;
}
