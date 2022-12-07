package com.example.MovieApiApp.client;

import com.example.MovieApiApp.config.OMDbClientConfig;
import com.example.MovieApiApp.dto.MovieDto;
import com.example.MovieApiApp.exception.MovieNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OMDbClientTestSuite {

    @InjectMocks
    private OMDbClient omDbClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private OMDbClientConfig omDbClientConfig;

    @Test
    void shouldFetchMovieByTitle() throws URISyntaxException, MovieNotFoundException {

        when(omDbClientConfig.getOmdbApiEndpoint()).thenReturn("http://test.com/");
        when(omDbClientConfig.getOmdbApiKey()).thenReturn("test");

        MovieDto movieDto = new MovieDto(
                "test",
                "test description",
                "test genre",
                "test director",
                "test poster"
        );

        URI uri = new URI("http://test.com/?t=test&apikey=test");

        when(restTemplate.getForObject(uri, MovieDto.class)).thenReturn(movieDto);

        //When
        MovieDto fetchedMovie = omDbClient.getMovieByTitle("test");
        //Then
        assertEquals("test", fetchedMovie.getTitle());
        assertEquals("test description", fetchedMovie.getDescription());
        assertEquals("test genre", fetchedMovie.getGenre());
        assertEquals("test director", fetchedMovie.getDirector());
        assertEquals("test poster", fetchedMovie.getPoster());
    }
}
