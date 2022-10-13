package com.mytrip.demo.infrastructure.weather.model;

import lombok.*;

import java.util.ArrayList;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather16dayResponse {

    private ArrayList<Weather16dayProperties> data;
    private String city_name;
    private Double lon;
    private String timezone;
    private Double lat;
    private String country_code;
    private String state_code;
}
