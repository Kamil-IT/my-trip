package com.mytrip.demo.infrastructure.geocoding.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GeocodingForwardRequest {

    String street;
    String city;
    String state;
    String postalcode;
    String country;
    @JsonProperty("accept-language")
    String acceptLanguage;
    String polygon_threshold;
}
