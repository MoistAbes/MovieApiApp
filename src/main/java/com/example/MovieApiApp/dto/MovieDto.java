package com.example.MovieApiApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class MovieDto {

    @JsonProperty("Title")
    private String title;
    @JsonProperty("Plot")
    private String description;
    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Poster")
    private String poster;

}
