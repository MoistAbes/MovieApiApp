package com.example.MovieApiApp.repository;

import com.example.MovieApiApp.entity.MovieEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, Long> {

    @Override
    List<MovieEntity> findAll();

    @Override
    MovieEntity save(MovieEntity movieEntity);

    @Override
    Optional<MovieEntity> findById(Long id);
}
