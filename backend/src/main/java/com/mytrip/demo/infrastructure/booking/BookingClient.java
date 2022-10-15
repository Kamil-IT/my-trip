package com.mytrip.demo.infrastructure.booking;

import com.mytrip.demo.application.exception.ResourceNotFoundException;
import com.mytrip.demo.application.port.in.booking.model.FindHotel;
import com.mytrip.demo.infrastructure.booking.model.BookingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BookingClient {
    //    https://rapidapi.com/apidojo/api/booking

    private final RestTemplate restTemplate;

    private static final String URL = "https://hotels-com-provider.p.rapidapi.com/v1/hotels/nearby";
    private static final String URL_TEMPLATE = UriComponentsBuilder.fromHttpUrl(URL)
            .queryParam("latitude", "{latitude}")
            .queryParam("longitude", "{longitude}")
            .queryParam("currency", "{currency}")
            .queryParam("checkout_date", "{checkout_date}")
            .queryParam("sort_order", "{sort_order}")
            .queryParam("checkin_date", "{checkin_date}")
            .queryParam("adults_number", "{adults_number}")
            .queryParam("locale", "{locale}")
            .queryParam("page_number", "{page_number}")
            .encode()
            .toUriString();

    public BookingResponse findHotels(FindHotel findHotel) {
        HttpHeaders headers = getHttpHeaders();
        Map<String, String> urlParams = getUrlParams(findHotel.getLatitude(), findHotel.getLongitude(),
                findHotel.getAdultsNumber(), findHotel.getFrom(), findHotel.getTo());

        ResponseEntity<BookingResponse> response = restTemplate.exchange(URL_TEMPLATE,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                BookingResponse.class,
                urlParams);

        return response.getBody();
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", "0d9a37f80fmsh1c307ea0fa859cep11e3e3jsn3a99d087cd8a");
        headers.add("X-RapidAPI-Host", "hotels-com-provider.p.rapidapi.com");
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }


    private static Map<String, String> getUrlParams(double lat, double lon, int adultsNumber, LocalDate from, LocalDate to) {
        Map<String, String> params = new HashMap<>();
        params.put("latitude", String.valueOf(lat));
        params.put("longitude", String.valueOf(lon));
        params.put("currency", "USD");
        params.put("checkin_date", from.toString());
        params.put("checkout_date", to.toString());
        params.put("sort_order", "STAR_RATING_HIGHEST_FIRST");
        params.put("adults_number", String.valueOf(adultsNumber));
        params.put("locale", "en_US");
        params.put("page_number", String.valueOf(1));
        return params;
    }


}
