package com.mytrip.demo.infrastructure.geocoding.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;

@Value
@Builder
public class GeocodingForwardResponse {

    Double importance;
    String licence;
    @JsonProperty("class")
    String myclass;
    GeocodingForwardAddress address;
    Long osm_id;
    String display_name;
    String osm_type;
    String lon;
    Integer place_id;
    ArrayList<String> boundingbox;
    String lat;
    String type;
}
