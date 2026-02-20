package com.example.imdb.graphql;

import com.example.imdb.dto.MovieInput;
import com.example.imdb.dto.MovieUpdateInput;
import com.example.imdb.model.Movie;
import com.example.imdb.service.MovieService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MovieGraphql {

    private final MovieService movieService;

    public MovieGraphql(MovieService movieService) {
        this.movieService = movieService;
    }

    @QueryMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @QueryMapping
    public Movie getMovieById(@Argument int id) {
        return movieService.getMovieById(id);
    }

    @QueryMapping
    public List<Movie> searchMoviesByTitle(@Argument String titleContains) {
        return movieService.searchMoviesByTitle(titleContains);
    }

    @MutationMapping
    public Movie createMovie(@Argument MovieInput movie) {
        return movieService.createMovie(movie);
    }

    @MutationMapping
    public Movie updateMovie(@Argument int id, @Argument MovieUpdateInput update) {
        return movieService.updateMovie(id, update);
    }

    @MutationMapping
    public String deleteMovie(@Argument int id) {
        return movieService.deleteMovie(id);
    }
}
