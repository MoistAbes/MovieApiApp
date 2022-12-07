package com.example.MovieApiApp.client;

import com.example.MovieApiApp.config.OMDbClientConfig;
import com.example.MovieApiApp.domain.Movie;
import com.example.MovieApiApp.dto.MovieDto;
import com.example.MovieApiApp.exception.MovieNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OMDbClient {

    private final RestTemplate restTemplate;
    private final OMDbClientConfig omDbClientConfig;

    public MovieDto getMovieByTitle(String title) throws MovieNotFoundException{

        URI url = UriComponentsBuilder.fromHttpUrl(omDbClientConfig.getOmdbApiEndpoint() + "?t=" + title + "&apikey=" + omDbClientConfig.getOmdbApiKey())
                .build()
                .encode()
                .toUri();



        MovieDto movieDto = restTemplate.getForObject(url, MovieDto.class);
        Optional<String> optionalTitle = Optional.ofNullable(movieDto.getTitle());


        if (optionalTitle.isPresent()){
            return movieDto;
        }else {
            throw new MovieNotFoundException();
        }


    }

}
