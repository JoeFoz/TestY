package com.example.weatherappv4.service;

// package com.example.weatherappv4.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.json.JSONObject;

@Service
public class WeatherServiceV4 {

    // Inject the OpenWeather API key from application.properties
    @Value("${openweather.api.key}")
    private String apiKey;

    // Base URL for the OpenWeatherMap API
    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}&units=metric";

    // Fetch weather data for a given city
    public JSONObject getWeather(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String url = WEATHER_URL.replace("{city}", city).replace("{apiKey}", apiKey);

        // Make the API request and return the JSON response
        String weatherData = restTemplate.getForObject(url, String.class);
        return new JSONObject(weatherData);
    }
}
