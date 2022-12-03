package com.example.MovieApiApp.repository;

import com.example.MovieApiApp.entity.MovieEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MovieRepositoryTestSuite {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void testMovieSave(){
        //Given
        MovieEntity movieEntity = new MovieEntity(
                "test title",
                "test desc",
                "test genre",
                "test director",
                "test poster"
        );

        //When
         movieRepository.save(movieEntity);

         //Then
        Long id = movieEntity.getId();
        Optional<MovieEntity> readMovie = movieRepository.findById(id);
        assertTrue(readMovie.isPresent());

        //CleanUp
        movieRepository.deleteById(id);
    }

    @Test
    void testMovieFindAll(){
        //Given
        MovieEntity movieEntity = new MovieEntity(
                "test title",
                "test desc",
                "test genre",
                "test director",
                "test poster"
        );
        MovieEntity movieEntity2 = new MovieEntity(
                "test title 2",
                "test desc 2",
                "test genre 2",
                "test director 2",
                "test poster 2"
        );
        MovieEntity movieEntity3 = new MovieEntity(
                "test title 3",
                "test desc 3",
                "test genre 3",
                "test director 3",
                "test poster 3"
        );

        //When
        movieRepository.save(movieEntity);
        Long movieEntityId = movieEntity.getId();
        movieRepository.save(movieEntity2);
        Long movieEntityId2 = movieEntity2.getId();
        movieRepository.save(movieEntity3);
        Long movieEntityId3 = movieEntity3.getId();

        //Then
        List<MovieEntity> readMovies;
        readMovies = movieRepository.findAll();
        assertEquals(3, readMovies.size());

        //CleanUp
        movieRepository.deleteById(movieEntityId);
        movieRepository.deleteById(movieEntityId2);
        movieRepository.deleteById(movieEntityId3);
    }
}
