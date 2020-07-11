package com.example.consumingrest.Service;

import com.example.consumingrest.Object.Quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteService {
    @Autowired
    private RestTemplate restTemplate;

    @RestResource(exported = true)
    public Quote getQuote() {
        return restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
    }
}