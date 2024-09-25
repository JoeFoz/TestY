package com.example.sentimentappv2;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentimentController {

    @GetMapping("/api/sentiment/analyze")
    public String analyzeSentiment(@RequestParam String businessName, @RequestParam String postcode) {
        // Mocked sentiment analysis response (replace with real logic later)
        JSONObject result = new JSONObject();
        result.put("businessName", businessName);
        result.put("postcode", postcode);
        result.put("sentiment", "Positive");  // Example: Fixed sentiment value

        return result.toString();
    }
}

