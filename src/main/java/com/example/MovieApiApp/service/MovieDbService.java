package com.example.MovieApiApp.service;

import com.example.MovieApiApp.entity.MovieEntity;
import com.example.MovieApiApp.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieDbService {

    private final MovieRepository repository;

    public List<MovieEntity> getAllMovies(){
        return repository.findAll();
    }

    public void saveMovie(MovieEntity movieEntity){
        repository.save(movieEntity);
    }
}
