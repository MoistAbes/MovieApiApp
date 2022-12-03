package com.example.MovieApiApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="favourite_movies")
@Getter
@NoArgsConstructor
public class MovieEntity {

    public MovieEntity(
            String title,
            String description,
            String genre,
            String director,
            String poster
    ){
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.director = director;
        this.poster = poster;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "genre")
    private String genre;

    @NotNull
    @Column(name = "director")
    private String director;

    @NotNull
    @Column(name = "poster")
    private String poster;



}
