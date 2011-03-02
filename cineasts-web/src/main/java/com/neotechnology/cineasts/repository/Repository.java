package com.neotechnology.cineasts.repository;

import com.neotechnology.cineasts.domain.Actor;
import com.neotechnology.cineasts.domain.Movie;

public interface Repository {
    Actor findActorById(String id);
    Movie findMovieById(String id);
    Iterable<Actor> findAllActors();
}
