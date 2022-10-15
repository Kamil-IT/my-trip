package com.mytrip.demo.application.port.out;

import com.mytrip.demo.application.persistance.trip.model.event.TripEventJpa;
import com.mytrip.demo.application.persistance.trip.model.event.type.TripEventTypeName;
import com.mytrip.demo.infrastructure.weather.WeatherClient;
import com.mytrip.demo.infrastructure.weather.model.Weather16dayProperties;
import com.mytrip.demo.infrastructure.weather.model.Weather16dayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class EventAdditionPropsService {

    private static final Set<TripEventTypeName> EVENT_TYPES_WITH_WEATHER =
            Set.of(TripEventTypeName.ACCOMMODATION, TripEventTypeName.TOUR);
    private final WeatherClient weatherClient;

    public void addAdditionalPropertiesToEvent(TripEventJpa eventCreated) {
        TripEventTypeName type = eventCreated.getTripType();
        Double latitude = eventCreated.getLatitude();
        Double longitude = eventCreated.getLongitude();
        LocalDate startDate = eventCreated.getStartDate();
        LocalDate endDate = eventCreated.getEndDate();

        if (EVENT_TYPES_WITH_WEATHER.contains(type) && startDate.isBefore(LocalDate.now().plusDays(16))){
            Weather16dayResponse weatherFor16Days = weatherClient.getWeatherFor16Days(latitude, longitude);

            final Integer[] dateCounter = {0};
            weatherFor16Days.getData().forEach(weather -> {
                if (weather.getDatetime().isAfter(startDate) && weather.getDatetime().isBefore(endDate)) {
                    eventCreated.addProperty(getWeatherPropVal(dateCounter), getWeatherPropVal(weather));
                    dateCounter[0] += 1;
                }
            });
        }
    }

    private static String getWeatherPropVal(Integer[] dateCounter) {
        return "weather_" + dateCounter[0];
    }

    private static String getWeatherPropVal(Weather16dayProperties weather) {
        return weather.getApp_min_temp() + "-" + weather.getApp_max_temp();
    }
}
