package com.mytrip.demo.application.port.out.impl;

import com.mytrip.demo.application.persistance.trip.model.event.type.TripEventTypeName;
import com.mytrip.demo.application.port.out.CreateAdditionalProps;
import com.mytrip.demo.domain.Event;
import com.mytrip.demo.infrastructure.weather.WeatherClient;
import com.mytrip.demo.infrastructure.weather.model.Weather16dayProperties;
import com.mytrip.demo.infrastructure.weather.model.Weather16dayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
class WeatherPropsService implements CreateAdditionalProps {

    private static final Set<TripEventTypeName> EVENT_TYPES_WITH_WEATHER =
            Set.of(TripEventTypeName.ACCOMMODATION, TripEventTypeName.TOUR);
    private final WeatherClient weatherClient;

    @Override
    public Map<String, String> createAdditionalPropertiesForEvent(Event event) {
        Optional<Map<String, String>> props = Optional.empty();

        TripEventTypeName type = event.getEventType();
        Double latitude = event.getLocation().getLatitude();
        Double longitude = event.getLocation().getLongitude();
        LocalDate startDate = event.getFrom();
        LocalDate endDate = event.getTo();

        if (isAbleToGetWeather(type, startDate)){
            Weather16dayResponse weatherFor16Days = weatherClient.getWeatherFor16Days(latitude, longitude);
            props = Optional.of(createWeatherProps(startDate, endDate, weatherFor16Days));
        }

        return props.orElse(new HashMap<>());
    }

    private static Map<String, String> createWeatherProps(LocalDate startDate, LocalDate endDate, Weather16dayResponse weatherFor16Days) {
        Map<String, String> props = new HashMap<>();

        final Integer[] dateCounter = {0};
        weatherFor16Days.getData().forEach(weather -> {
            if (weather.getDatetime().isAfter(startDate) && weather.getDatetime().isBefore(endDate)) {
                props.put(getWeatherPropVal(dateCounter), getWeatherPropVal(weather));
                dateCounter[0] += 1;
            }
        });

        return props;
    }

    private static boolean isAbleToGetWeather(TripEventTypeName type, LocalDate startDate) {
        return EVENT_TYPES_WITH_WEATHER.contains(type) &&
                startDate.isBefore(LocalDate.now().plusDays(16));
    }

    private static String getWeatherPropVal(Integer[] dateCounter) {
        return "weather_" + dateCounter[0];
    }

    private static String getWeatherPropVal(Weather16dayProperties weather) {
        return weather.getApp_min_temp() + "-" + weather.getApp_max_temp();
    }
}
