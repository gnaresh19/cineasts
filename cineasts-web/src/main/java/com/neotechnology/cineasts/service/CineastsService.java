package com.neotechnology.cineasts.service;

import com.neotechnology.cineasts.domain.Account;
import com.neotechnology.cineasts.domain.Actor;
import com.neotechnology.cineasts.domain.Movie;

public interface CineastsService {
    Actor findActorById(String id);
    Movie findMovieById(String id);
    Account findAccountByUsername(String username);
    void save(Account unAttachedInstance);
    Iterable<Actor> findAllActors();
	Iterable<Movie> findAllMovies();
	Iterable<Account> findAllAccounts();
}
