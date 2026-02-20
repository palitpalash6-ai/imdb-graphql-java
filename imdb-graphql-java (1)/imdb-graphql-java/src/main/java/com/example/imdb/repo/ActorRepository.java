package com.example.imdb.repo;

import com.example.imdb.model.ActorDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ActorRepository extends MongoRepository<ActorDoc, String> {
    Optional<ActorDoc> findByIdIs(Integer id);
    void deleteByIdIs(Integer id);

    List<ActorDoc> findByNameContainingIgnoreCase(String nameContains);
}
