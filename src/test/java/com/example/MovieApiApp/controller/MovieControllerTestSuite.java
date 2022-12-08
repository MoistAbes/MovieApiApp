package com.example.MovieApiApp.controller;

import com.example.MovieApiApp.dto.MovieDto;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(MovieController.class)
public class MovieControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieController movieController;

    @Test
    void shouldFetchFavouriteMovies() throws Exception{

        //Given
        MovieDto movieDto = new MovieDto(
                "test title",
                "test description",
                "test genre",
                "test director",
                "test poster"
        ); MovieDto movieDto2 = new MovieDto(
                "test title2",
                "test description2",
                "test genre2",
                "test director2",
                "test poster2"
        );

        List<MovieDto> movieDtoList = List.of(movieDto, movieDto2);

        ResponseEntity<List<MovieDto>> resultMovies = new ResponseEntity<>(movieDtoList, HttpStatus.OK);

        when(movieController.getMovies()).thenReturn(resultMovies);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/movies/favourites")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].Title", Matchers.is("test title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].Plot", Matchers.is("test description")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].Genre", Matchers.is("test genre")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].Director", Matchers.is("test director")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].Poster", Matchers.is("test poster")));
    }

    @Test
    void shouldFetchMovie() throws Exception{
        //Given
        MovieDto movieDto = new MovieDto(
                "test title",
                "test description",
                "test genre",
                "test director",
                "test poster"
        );

        ResponseEntity<MovieDto> resultMovie = new ResponseEntity<>(movieDto, HttpStatus.OK);

        when(movieController.getMovie("test title")).thenReturn(resultMovie);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/movies/test title")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.Title", Matchers.is("test title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.Plot", Matchers.is("test description")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.Genre", Matchers.is("test genre")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.Director", Matchers.is("test director")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.Poster", Matchers.is("test poster")));

    }

    @Test
    void shouldAddMove() throws Exception{

        //Given
        MovieDto movieDto = new MovieDto(
                "test title",
                "test description",
                "test genre",
                "test director",
                "test poster"
        );
        ResponseEntity<Void> responseEntity = ResponseEntity.ok().build();

        when(movieController.addMovie(any(MovieDto.class))).thenReturn(responseEntity);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(movieDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().isOk());

    }

    @Test
    void shouldAddMovieToFavourites() throws Exception {
        //Given
        MovieDto movieDto = new MovieDto(
                "test title",
                "test description",
                "test genre",
                "test director",
                "test poster"
        );
        ResponseEntity<Void> responseEntity = ResponseEntity.ok().build();

        when(movieController.addMovieToFavourites("test title")).thenReturn(responseEntity);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/movies/test title")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
