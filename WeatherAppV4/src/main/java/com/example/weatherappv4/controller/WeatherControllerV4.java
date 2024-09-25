package com.example.weatherappv4.controller;

//package com.example.weatherappv4.controller;

import com.example.weatherappv4.service.WeatherServiceV4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;

@RestController
@RequestMapping("/v4weather")
public class WeatherControllerV4 {

    @Autowired
    private WeatherServiceV4 weatherService;

    // Handle GET requests at /v4weather/{city}
    @GetMapping("/{city}")
    public String getWeather(@PathVariable String city) {
        JSONObject weatherData = weatherService.getWeather(city);

        // Extract and format relevant data
        JSONObject result = new JSONObject();
        result.put("city", weatherData.getString("name"));
        result.put("temperature", weatherData.getJSONObject("main").getDouble("temp"));
        result.put("description", weatherData.getJSONArray("weather").getJSONObject(0).getString("description"));
        result.put("humidity", weatherData.getJSONObject("main").getInt("humidity"));
        result.put("windSpeed", weatherData.getJSONObject("wind").getDouble("speed"));

        // Return the formatted JSON as a string
        return result.toString();
    }
}
