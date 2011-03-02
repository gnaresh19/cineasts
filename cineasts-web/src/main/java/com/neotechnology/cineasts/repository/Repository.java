package com.neotechnology.cineasts.repository;

import com.neotechnology.cineasts.domain.Actor;
import com.neotechnology.cineasts.domain.Movie;

public interface Repository {
    Actor findActorById(long id);
    Movie findMovieById(long id);
    Iterable<Actor> findAllActors();
}
