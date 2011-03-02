package com.neotechnology.cineasts.service;

import com.neotechnology.cineasts.domain.Actor;
import com.neotechnology.cineasts.domain.Movie;

public interface CineastsService {
    Actor findActorById(String id);
    Movie findMovieById(String id);
    Iterable<Actor> findAllActors();
}
