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
    
    private static final int NONEXISTENT_ID = 1000;
    
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
        Actor actor = new Actor();
        Actor retrievedActor = repository.findActorById(actor.getNodeId());
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
        Movie movie = new Movie();
        Movie retrievedMovie = repository.findMovieById(movie.getNodeId());
        assertNotNull(retrievedMovie);
    }

    @Test
    @Transactional
    public void testFindMovieByNonexistentId() {
        Movie retrievedMovie = repository.findMovieById(NONEXISTENT_ID);
        assertNull(retrievedMovie);
    }
}
