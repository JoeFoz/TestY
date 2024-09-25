package com.example.sentimentapp.service;

import edu.stanford.nlp.pipeline.*;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class SentimentService {

    private StanfordCoreNLP pipeline;

    public SentimentService() {
        // Setting up the Stanford NLP pipeline for sentiment analysis
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,parse,sentiment");
        this.pipeline = new StanfordCoreNLP(props);
    }

    public String analyzeSentiment(String text) {
        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);

        // Analyze sentiment and return the result for the first sentence
        return document.sentences().get(0).sentiment().toString();
    }
}