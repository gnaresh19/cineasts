package com.neotechnology.cineasts.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.neotechnology.cineasts.domain.Actor;
import com.neotechnology.cineasts.domain.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/sdgRepositoryTestApplicationContext.xml"})
public class RepositoryTest {
    
    private static final String MOVIE_ID_1 = "1";
    private static final String ACTOR_NAME_1 = "Peter Weller";
    private static final String ACTOR_ID_1 = "1";
    private static final String NONEXISTENT_ID = "nonexistent";
    private static final String MOVIE_NAME_1 = "Robocop";
    
    @Autowired
    SDGRepository repository;

    @Test
    @Transactional
    public void testPopulate() {
        repository.populateWithSomeTestData();
    }
    
    @Test
    @Transactional
    public void testFindActorById() {
        Actor actor = new Actor(ACTOR_ID_1, ACTOR_NAME_1);
        Actor retrievedActor = repository.findActorById(ACTOR_ID_1);
        assertNotNull(retrievedActor);
    }

    @Test
    @Transactional
    public void testFindActorByNonexistentId() {
        Actor retrievedActor = repository.findActorById(NONEXISTENT_ID);
        assertNull(retrievedActor);
    }
    
    @Test
    @Transactional
    public void testFindMovieById() {
        Movie movie = new Movie(MOVIE_ID_1, MOVIE_NAME_1);
        Movie retrievedMovie = repository.findMovieById(MOVIE_ID_1);
        assertNotNull(retrievedMovie);
    }

    @Test
    @Transactional
    public void testFindMovieByNonexistentId() {
        Movie retrievedMovie = repository.findMovieById(NONEXISTENT_ID);
        assertNull(retrievedMovie);
    }
    
    @Test
    @Transactional
    public void testIndexSeparationBetweenMoviesAndActors() {
        Movie movie = new Movie(MOVIE_ID_1, MOVIE_NAME_1);
        Actor actor = new Actor(MOVIE_ID_1, ACTOR_NAME_1);
        Movie retrievedMovie = repository.findMovieById(MOVIE_ID_1);
        assertNotNull(retrievedMovie);
        assertEquals(MOVIE_ID_1, retrievedMovie.getId());
    }
}
