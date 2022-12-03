package com.example.MovieApiApp.mapper;

import com.example.MovieApiApp.dto.MovieDto;
import com.example.MovieApiApp.entity.MovieEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieMapper {

    public MovieEntity mapToMovieEntity(MovieDto movieDto){
        return new MovieEntity(
                movieDto.getTitle(),
                movieDto.getDescription(),
                movieDto.getGenre(),
                movieDto.getDirector(),
                movieDto.getPoster()
        );
    }

    public MovieDto mapToMovieDto(MovieEntity movieEntity){
        return new MovieDto(
                movieEntity.getTitle(),
                movieEntity.getDescription(),
                movieEntity.getGenre(),
                movieEntity.getDirector(),
                movieEntity.getPoster()
        );
    }

    public List<MovieDto> mapToMovieDtoList(List<MovieEntity> movieEntityList){

        return movieEntityList.stream()
                .map(this::mapToMovieDto)
                .collect(Collectors.toList());

    }
}
