package com.example.sentimentapp.controller;

import com.example.sentimentapp.service.SentimentService;
import com.example.sentimentapp.service.SocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/sentiment")
public class SentimentController {

    @Autowired
    private SentimentService sentimentService;

    @Autowired
    private SocialMediaService socialMediaService;

    @GetMapping("/analyze")
    public List<Map<String, String>> analyze(@RequestParam String businessName, @RequestParam String postcode) {
        // Fetch comments from Facebook and Google
        List<String> comments = new ArrayList<>();
        comments.addAll(socialMediaService.getFacebookComments(businessName));
        comments.addAll(socialMediaService.getGoogleReviews(businessName, postcode));

        // Analyze sentiment for each comment
        List<Map<String, String>> analysisResults = new ArrayList<>();
        for (String comment : comments) {
            Map<String, String> result = new HashMap<>();
            result.put("comment", comment);
            result.put("sentiment", sentimentService.analyzeSentiment(comment));
            analysisResults.add(result);
        }
        return analysisResults;
    }
}
