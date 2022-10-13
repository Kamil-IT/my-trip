package com.mytrip.demo.infrastructure.geocoding.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeocodingForwardResponse {

    private Double importance;
    private String licence;
    @JsonProperty("class")
    private String myclass;
    private Long osm_id;
    private String display_name;
    private String osm_type;
    private String lon;
    private Integer place_id;
    private List<String> boundingbox;
    private String lat;
    private String type;
}
