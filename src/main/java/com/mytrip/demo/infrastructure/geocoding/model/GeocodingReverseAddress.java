package com.mytrip.demo.infrastructure.geocoding.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GeocodingReverseAddress {
    String state;
    String country;
    String city;
    String country_code;
    String county;
}
