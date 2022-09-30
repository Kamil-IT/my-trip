package com.mytrip.demo.infrastructure.geocoding.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;

@Value
@Builder
public class GeocodingForwardAddress {

    String road;
    String state;
    String county;
    String country_code;
    String house_number;
    String city;
    String suburb;
    String country;
    String neighbourhood;
    String amenity;
    String postcode;
}
