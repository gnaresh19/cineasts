package com.neotechnology.cineasts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.graph.neo4j.finder.FinderFactory;
import org.springframework.data.graph.neo4j.support.GraphDatabaseContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.neotechnology.cineasts.domain.Account;
import com.neotechnology.cineasts.domain.Actor;
import com.neotechnology.cineasts.domain.Movie;
import com.neotechnology.cineasts.domain.Rating;
import com.neotechnology.cineasts.util.AuthenticationUtils;

@Component
public class SpringDataGraphCineastsService implements com.neotechnology.cineasts.service.CineastsService {

    @Autowired
    private FinderFactory finderFactory;
    
    @Autowired
    private GraphDatabaseContext graphDatabaseContext;

    @Transactional
    public void populateWithSomeTestData() {
        System.out.println("==== Populating ====");
        if (!graphDatabaseContext.transactionIsRunning()) {
            throw new RuntimeException("No transaction running");
        }
        new Actor("1", "Peter Weller");
        new Actor("2", "Ronny Cox");
        new Actor("3", "Kurtwood Smith");
        
        new Movie("1", "Robocop");
        Movie movie = new Movie("2", "Planet Terror");
        
        
        Account account  = new Account("johanr", "johan", "johan.rask@jayway.com");
        account.attach();
        Rating rating = account.relateTo(movie, Rating.class,"RATING");
        rating.setComment("Ok movie if you are unable to sleep..");
        rating.setRating(2);
        
    }

    /**
     * Once an Account has been created it can simly be attached to the graph
     * with this method.
     */
    @Transactional
	@Override
	public void save(Account unAttachedInstance) {
    	unAttachedInstance.attach();
	}
    
    
    @Override
    public Actor findActorById(String id) {
        return finderFactory.createNodeEntityFinder( Actor.class ).findByPropertyValue("actor_id", "id", id);
    }

    @Override
    public Movie findMovieById(String id) {
        return finderFactory.createNodeEntityFinder( Movie.class ).findByPropertyValue("movie_id", "id", id);
    }
    
    @Override
    public Iterable<Movie> findAllMovies() {
        return finderFactory.createNodeEntityFinder( Movie.class ).findAll();        
    }
    
    @Override
    public Iterable<Actor> findAllActors() {
        return finderFactory.createNodeEntityFinder( Actor.class ).findAll();        
    }

	@Override
	public Account findAccountByUsername(String username) {
		return finderFactory.createNodeEntityFinder( Account.class ).findByPropertyValue("username", "username", username);
	}

	@Override
    public Iterable<Account> findAllAccounts() {
        return finderFactory.createNodeEntityFinder( Account.class ).findAll();        
    }

	@Override
	@Transactional
	public void save(Movie movie) {
	    movie.attach();
	}

    @Override
    public void save(Actor person) {
        person.attach();
    }
}
