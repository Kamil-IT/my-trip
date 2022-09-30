package com.mytrip.demo.infrastructure.weather.model;

import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;

@Builder
@Value
public class Weather16dayResponse {

    ArrayList<Weather16dayProperties> data;
    String city_name;
    Double lon;
    String timezone;
    Double lat;
    String country_code;
    String state_code;
}
