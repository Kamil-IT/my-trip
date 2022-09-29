package com.mytrip.demo.infrastructure.geocoding;

import com.mytrip.demo.infrastructure.geocoding.model.GeocodingForwardRequest;
import com.mytrip.demo.infrastructure.geocoding.model.GeocodingForwardResponse;
import com.mytrip.demo.infrastructure.geocoding.model.GeocodingReverseResponse;

public class GeocodingClient {

//    https://rapidapi.com/GeocodeSupport/api/forward-reverse-geocoding

    public GeocodingReverseResponse getLocation(double latitude, double longitude) {
        return GeocodingReverseResponse.builder().build();
    }

    public GeocodingForwardResponse getCoordinates(GeocodingForwardRequest geocodingForwardRequest) {
        return GeocodingForwardResponse.builder().build();
    }
}
