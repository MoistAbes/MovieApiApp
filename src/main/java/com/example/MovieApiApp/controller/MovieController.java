package com.example.MovieApiApp.controller;

import com.example.MovieApiApp.client.OMDbClient;
import com.example.MovieApiApp.dto.MovieDto;
import com.example.MovieApiApp.mapper.MovieMapper;
import com.example.MovieApiApp.service.MovieDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final OMDbClient omDbClient;
    private final MovieDbService service;
    private final MovieMapper mapper;

    @GetMapping
    public List<MovieDto> getMovies(){
        return mapper.mapToMovieDtoList(service.getAllMovies());
    }

    @GetMapping(value = "{title}")
    public MovieDto getMovie(@PathVariable String title){
        return omDbClient.getMovieByTitle(title);
    }

//    public MovieDto addMovie(){
//       return new MovieDto();
//    }
}
