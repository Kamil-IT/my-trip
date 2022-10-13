package com.mytrip.demo.application.port.out;

import com.mytrip.demo.application.persistance.trip.event.TripEventJpa;
import com.mytrip.demo.application.persistance.trip.event.properties.TripEventTypePropertiesJpa;
import com.mytrip.demo.application.persistance.trip.event.type.TripEventTypeName;
import com.mytrip.demo.infrastructure.weather.WeatherClient;
import com.mytrip.demo.infrastructure.weather.model.Weather16dayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class EventAdditionPropsService {

    private final WeatherClient weatherClient;

    public void addAdditionalPropertiesToEvent(TripEventJpa eventCreated) {
        TripEventTypeName type = eventCreated.getTripType();
        Double latitude = eventCreated.getLatitude();
        Double longitude = eventCreated.getLongitude();
        LocalDate startDate = eventCreated.getStartDate();

        if ((TripEventTypeName.ACCOMMODATION.equals(type) || TripEventTypeName.TOUR.equals(type)) && startDate.isBefore(LocalDate.now().plusDays(16))){
            Weather16dayResponse weatherFor16Days = weatherClient.getWeatherFor16Days(latitude, longitude);
            final Integer[] dateCounter = {0};
            weatherFor16Days.getData().forEach(weather -> {
                if (weather.getDatetime().isAfter(startDate)) {
                    eventCreated.addProperty("weather_" + dateCounter[0], weather.getApp_min_temp() + "-" + weather.getApp_max_temp());
                    dateCounter[0] += 1;
                }
            });
        }
    }
}
