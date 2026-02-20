package com.example.imdb.repo;

import com.example.imdb.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends MongoRepository<Movie, String> {
    Optional<Movie> findByIdIs(Integer id);
    void deleteByIdIs(Integer id);

    List<Movie> findByTitleContainingIgnoreCase(String titleContains);
}
