package com.mytrip.demo.infrastructure.geocoding;

import com.mytrip.demo.infrastructure.geocoding.model.GeocodingForwardRequest;
import com.mytrip.demo.infrastructure.geocoding.model.GeocodingForwardResponse;
import com.mytrip.demo.infrastructure.geocoding.model.GeocodingReverseResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeocodingClient {
    //    https://rapidapi.com/GeocodeSupport/api/forward-reverse-geocoding

    public static final String URL = "https://forward-reverse-geocoding.p.rapidapi.com";

    public GeocodingReverseResponse getLocation(double latitude, double longitude) {
        return GeocodingReverseResponse.builder().build();
    }

    public GeocodingForwardResponse getCoordinates(GeocodingForwardRequest geocodingForwardRequest) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GeocodingForwardResponse> response
                = restTemplate.exchange(URL, HttpMethod.GET, new HttpEntity<>(geocodingForwardRequest), GeocodingForwardResponse.class);

        return response.getBody();
    }
}
