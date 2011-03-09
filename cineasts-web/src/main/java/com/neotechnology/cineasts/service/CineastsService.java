package com.neotechnology.cineasts.service;

import com.neotechnology.cineasts.domain.Account;
import com.neotechnology.cineasts.domain.Actor;
import com.neotechnology.cineasts.domain.Movie;

public interface CineastsService {
    Actor findActorById(String id);
    Movie findMovieById(String id);
    Account findAccountByUsername(String username);
    // TODO: We need only one save() method: save(NodeBacked). Replace save() methods below.
    void save(Account unAttachedInstance);
    void save(Movie movie);
    Iterable<Actor> findAllActors();
	Iterable<Movie> findAllMovies();
	Iterable<Account> findAllAccounts();
    void save(Actor person);
}
