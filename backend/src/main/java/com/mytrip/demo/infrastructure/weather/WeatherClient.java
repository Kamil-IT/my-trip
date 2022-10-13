package com.mytrip.demo.infrastructure.weather;

import com.mytrip.demo.infrastructure.geocoding.model.GeocodingForwardResponse;
import com.mytrip.demo.infrastructure.weather.model.Weather16dayProperties;
import com.mytrip.demo.infrastructure.weather.model.Weather16dayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WeatherClient {
    // TODO: make call for weather to https://rapidapi.com/weatherbit/api/weather

    private final RestTemplate restTemplate;

    public static final String URL = "https://weatherbit-v1-mashape.p.rapidapi.com/forecast/daily";

    public Weather16dayResponse getWeatherFor16Days(double latitude, double longitude) {
        String url = URL + "?lat=" + latitude + "&lon=" + longitude;

        ResponseEntity<Weather16dayResponse> response =
                restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(getHttpHeaders()), Weather16dayResponse.class);

        return response.getBody();
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", "0d9a37f80fmsh1c307ea0fa859cep11e3e3jsn3a99d087cd8a");
        headers.add("X-RapidAPI-Host", "weatherbit-v1-mashape.p.rapidapi.com");
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }
}
