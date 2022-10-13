package com.mytrip.demo.infrastructure.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hotel {

    private Integer id;
    private String name;
    private String starRating;
    private Address address;
    private Coordinate coordinate;
    private OptimizedThumbUrls optimizedThumbUrls;
}
