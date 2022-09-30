package com.mytrip.demo.infrastructure.geocoding.model;

import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;

@Value
@Builder
public class GeocodingReverseResponse {

    String licence;
    Integer osm_id;
    GeocodingReverseAddress address;
    String osm_type;
    ArrayList<String> boundingbox;
    Integer place_id;
    String lat;
    String lon;
    String display_name;
}
