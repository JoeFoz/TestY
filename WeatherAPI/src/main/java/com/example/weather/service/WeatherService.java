package com.example.weather.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final String apiKey = "bc60d908515f25952aa70d23597113c8";
    private final String urlTemplate = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";

    public String getWeather(String city) {
        String url = String.format(urlTemplate, city, apiKey);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
