package com.example.MovieApiApp.client;

import com.example.MovieApiApp.domain.Movie;
import com.example.MovieApiApp.dto.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class OMDbClient {

    private final RestTemplate restTemplate;

    @Value("${omdb.api.key}")
    private String omdbApiKey;

    @Value("${omdb.api.endpoint}")
    private String omdbApiEndpoint;



    public MovieDto getMovieByTitle(String title){

        MovieDto movieDto =
                restTemplate.getForObject(omdbApiEndpoint + "?t=" + title + "&apikey=" + omdbApiKey,
                MovieDto.class);


        System.out.println(movieDto.getTitle());

        return movieDto;

    }

}
