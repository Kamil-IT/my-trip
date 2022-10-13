package com.mytrip.demo.infrastructure.geocoding;

import com.mytrip.demo.infrastructure.geocoding.model.GeocodingForwardRequest;
import com.mytrip.demo.infrastructure.geocoding.model.GeocodingForwardResponse;
import com.mytrip.demo.infrastructure.geocoding.model.GeocodingReverseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class GeocodingClient {
    //    https://rapidapi.com/GeocodeSupport/api/forward-reverse-geocoding

    private final RestTemplate restTemplate;

    public static final String URL = "https://forward-reverse-geocoding.p.rapidapi.com/v1/forward";

    public GeocodingReverseResponse getLocation(double latitude, double longitude) {
        return GeocodingReverseResponse.builder().build();
    }

    public GeocodingForwardResponse getCoordinates(GeocodingForwardRequest geocodingForwardRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", "0d9a37f80fmsh1c307ea0fa859cep11e3e3jsn3a99d087cd8a");
        headers.add("X-RapidAPI-Host", "forward-reverse-geocoding.p.rapidapi.com");
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        String url = URL + "?city=" + geocodingForwardRequest.getCity();

        ResponseEntity<List<GeocodingForwardResponse>> response =
                restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {});

        return Objects.requireNonNull(response.getBody()).stream().findAny()
                .orElseThrow(() -> new IllegalArgumentException("Not found city"));
    }
}
