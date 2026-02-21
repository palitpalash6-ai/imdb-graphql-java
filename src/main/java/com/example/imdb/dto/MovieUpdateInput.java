package com.example.imdb.dto;

import jakarta.validation.Valid;
import java.util.List;

public class MovieUpdateInput {

    private String title;
    private List<String> genres;
    private String description;
    private String director;

    @Valid
    private List<ActorInput> actors;

    private Integer year;
    private Integer runtime;
    private Double rating;
    private Integer votes;
    private Double revenue;

    public MovieUpdateInput() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<String> getGenres() { return genres; }
    public void setGenres(List<String> genres) { this.genres = genres; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public List<ActorInput> getActors() { return actors; }
    public void setActors(List<ActorInput> actors) { this.actors = actors; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public Integer getRuntime() { return runtime; }
    public void setRuntime(Integer runtime) { this.runtime = runtime; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public Integer getVotes() { return votes; }
    public void setVotes(Integer votes) { this.votes = votes; }

    public Double getRevenue() { return revenue; }
    public void setRevenue(Double revenue) { this.revenue = revenue; }
}
