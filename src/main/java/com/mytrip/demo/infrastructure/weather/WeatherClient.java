package com.mytrip.demo.infrastructure.weather;

import com.mytrip.demo.infrastructure.weather.model.Weather16dayProperties;

public class WeatherClient {

    public Weather16dayProperties getWeatherFor16Days(double latitude, double longitude){
//        TODO: make call for weather to https://rapidapi.com/weatherbit/api/weather
        return Weather16dayProperties.builder().build();
    }
}
