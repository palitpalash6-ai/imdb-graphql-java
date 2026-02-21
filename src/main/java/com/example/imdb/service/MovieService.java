package com.example.imdb.service;

import com.example.imdb.dto.ActorInput;
import com.example.imdb.dto.MovieInput;
import com.example.imdb.dto.MovieUpdateInput;
import com.example.imdb.model.Actor;
import com.example.imdb.model.Movie;
import com.example.imdb.repo.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepo;
    private final ActorService actorService;

    public MovieService(MovieRepository movieRepo, ActorService actorService) {
        this.movieRepo = movieRepo;
        this.actorService = actorService;
    }

    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    public Movie getMovieById(int id) {
        return movieRepo.findByIdIs(id).orElse(null);
    }

    public List<Movie> searchMoviesByTitle(String titleContains) {
        return movieRepo.findByTitleContainingIgnoreCase(titleContains);
    }

    public Movie createMovie(MovieInput input) {
        Movie m = new Movie();
        applyInputToMovie(m, input);
        // also upsert actors into actors collection
        upsertActorsFromMovie(m);
        return movieRepo.save(m);
    }

    public Movie updateMovie(int id, MovieUpdateInput update) {
        Movie existing = movieRepo.findByIdIs(id)
                .orElseThrow(() -> new NotFoundException("Movie not found: " + id));

        // apply partial updates
        if (update.getTitle() != null) existing.setTitle(update.getTitle());
        if (update.getGenres() != null) existing.setGenres(update.getGenres());
        if (update.getDescription() != null) existing.setDescription(update.getDescription());
        if (update.getDirector() != null) existing.setDirector(update.getDirector());
        if (update.getActors() != null) existing.setActors(convertActors(update.getActors()));
        if (update.getYear() != null) existing.setYear(update.getYear());
        if (update.getRuntime() != null) existing.setRuntime(update.getRuntime());
        if (update.getRating() != null) existing.setRating(update.getRating());
        if (update.getVotes() != null) existing.setVotes(update.getVotes());
        if (update.getRevenue() != null) existing.setRevenue(update.getRevenue());

        upsertActorsFromMovie(existing);
        return movieRepo.save(existing);
    }

    public String deleteMovie(int id) {
        movieRepo.deleteByIdIs(id);
        return "Movie deleted: " + id;
    }

    private void applyInputToMovie(Movie m, MovieInput input) {
        m.setId(input.getId());
        m.setTitle(input.getTitle());
        m.setGenres(input.getGenres());
        m.setDescription(input.getDescription());
        m.setDirector(input.getDirector());
        m.setActors(convertActors(input.getActors()));
        m.setYear(input.getYear());
        m.setRuntime(input.getRuntime());
        m.setRating(input.getRating());
        m.setVotes(input.getVotes());
        m.setRevenue(input.getRevenue());
    }

    private List<Actor> convertActors(List<ActorInput> inputs) {
        if (inputs == null) return null;
        List<Actor> out = new ArrayList<>();
        for (ActorInput a : inputs) {
            out.add(new Actor(a.getId(), a.getName()));
        }
        return out;
    }

    private void upsertActorsFromMovie(Movie m) {
        if (m.getActors() == null) return;
        for (Actor a : m.getActors()) {
            if (a.getId() != null && a.getName() != null) {
                actorService.upsertActor(a.getId(), a.getName());
            }
        }
    }
}
