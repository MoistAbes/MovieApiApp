package com.example.MovieApiApp.controller;

import com.example.MovieApiApp.client.OMDbClient;
import com.example.MovieApiApp.dto.MovieDto;
import com.example.MovieApiApp.entity.MovieEntity;
import com.example.MovieApiApp.exception.MovieNotFoundException;
import com.example.MovieApiApp.mapper.MovieMapper;
import com.example.MovieApiApp.service.MovieDbService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("v1/movies")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MovieController {

    private final OMDbClient omDbClient;
    private final MovieDbService service;
    private final MovieMapper mapper;

    @GetMapping(value = "/favourites")
    public ResponseEntity<List<MovieDto>> getMovies(){
        return ResponseEntity.ok(mapper.mapToMovieDtoList(service.getAllMovies()));
    }

    @GetMapping(value = "/{title}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable String title) throws MovieNotFoundException {
        MovieDto movieDto = omDbClient.getMovieByTitle(title);
        return ResponseEntity.ok(movieDto);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addMovie(@RequestBody MovieDto movieDto){
        MovieEntity movieEntity = mapper.mapToMovieEntity(movieDto);
        service.saveMovie(movieEntity);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{title}")
    public ResponseEntity<Void> addMovieToFavourites(@PathVariable String title) throws MovieNotFoundException{
        MovieEntity movieEntity = mapper.mapToMovieEntity(omDbClient.getMovieByTitle(title));
        service.saveMovie(movieEntity);
        return ResponseEntity.ok().build();
    }
}
