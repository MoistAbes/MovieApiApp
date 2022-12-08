package com.example.MovieApiApp.mapper;

import com.example.MovieApiApp.dto.MovieDto;
import com.example.MovieApiApp.entity.MovieEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MovieMapperTest {

    @Autowired
    MovieMapper mapper;

    @Test
    void shouldMapToMovieDto(){

        //Given
        MovieEntity movieEntity = new MovieEntity(
                "test title",
                "test description",
                "test genre",
                "test director",
                "test poster"
        );

        //When
        MovieDto movieDto = mapper.mapToMovieDto(movieEntity);

        //Then
        assertEquals(movieEntity.getTitle(), movieDto.getTitle());
        assertEquals(movieEntity.getDescription(), movieDto.getDescription());
        assertEquals(movieEntity.getGenre(), movieDto.getGenre());
        assertEquals(movieEntity.getDirector(), movieDto.getDirector());
        assertEquals(movieEntity.getPoster(), movieDto.getPoster());
    }

    @Test
    void shouldMapToMovieEntity(){
        //Given
        MovieDto movieDto = new MovieDto(
                "test title",
                "test description",
                "test genre",
                "test director",
                "test poster"
        );

        //When
        MovieEntity movieEntity = mapper.mapToMovieEntity(movieDto);

        //Then
        assertEquals(movieEntity.getTitle(), movieDto.getTitle());
        assertEquals(movieEntity.getDescription(), movieDto.getDescription());
        assertEquals(movieEntity.getGenre(), movieDto.getGenre());
        assertEquals(movieEntity.getDirector(), movieDto.getDirector());
        assertEquals(movieEntity.getPoster(), movieDto.getPoster());
    }

    @Test
    void shouldMapToMovieDtoList(){
        //Given
        MovieEntity movieEntity = new MovieEntity(
                "test title",
                "test description",
                "test genre",
                "test director",
                "test poster"
        );
        MovieEntity movieEntity2 = new MovieEntity(
                "test title2",
                "test description2",
                "test genre2",
                "test director2",
                "test poster2"
        );
        MovieEntity movieEntity3 = new MovieEntity(
                "test title3",
                "test description3",
                "test genre3",
                "test director3",
                "test poster3"
        );

        List<MovieEntity> movieEntityList = List.of(movieEntity, movieEntity2, movieEntity3);

        //When
        List<MovieDto> movieDtoList = mapper.mapToMovieDtoList(movieEntityList);

        //Then
        assertEquals(movieEntityList.size(), movieDtoList.size());

        for (int i = 0;i < movieEntityList.size(); i++){
            assertEquals(
                    movieEntityList.get(i).getTitle(),
                    movieDtoList.get(i).getTitle()
            );
            assertEquals(
                    movieEntityList.get(i).getDescription(),
                    movieDtoList.get(i).getDescription()
            );
            assertEquals(
                    movieEntityList.get(i).getGenre(),
                    movieDtoList.get(i).getGenre()
            );
            assertEquals(
                    movieEntityList.get(i).getDirector(),
                    movieDtoList.get(i).getDirector()
            );
            assertEquals(
                    movieEntityList.get(i).getPoster(),
                    movieDtoList.get(i).getPoster()
            );
        }
    }
}
