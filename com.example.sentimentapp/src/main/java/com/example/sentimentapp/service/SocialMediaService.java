package com.example.sentimentapp.service;

import com.restfb.*;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.google.maps.*;
import com.google.maps.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SocialMediaService {

    private static final String FACEBOOK_ACCESS_TOKEN = "your_facebook_access_token";
    private static final String GOOGLE_API_KEY = "your_google_api_key";

    public List<String> getFacebookComments(String businessName) {
        FacebookClient fbClient = new DefaultFacebookClient(FACEBOOK_ACCESS_TOKEN, Version.LATEST);
        Page page = fbClient.fetchObject(businessName, Page.class, Parameter.with("fields", "posts{message}"));

        List<String> comments = new ArrayList<>();
        if (page != null && page.getPosts() != null) {
            for (Post post : page.getPosts().getData()) {
                comments.add(post.getMessage());
            }
        }
        return comments;
    }

    public List<String> getGoogleReviews(String businessName, String postcode) {
        GeoApiContext context = new GeoApiContext.Builder().apiKey(GOOGLE_API_KEY).build();
        List<String> reviews = new ArrayList<>();

        try {
            PlacesSearchResponse response = PlacesApi.textSearchQuery(context, businessName + " " + postcode).await();
            for (PlacesSearchResult result : response.results) {
                PlaceDetails details = PlacesApi.placeDetails(context, result.placeId).await();
                for (PlaceDetails.Review review : details.reviews) {
                    reviews.add(review.text);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;
    }
}

