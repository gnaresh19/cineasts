package com.neotechnology.cineasts.moviedbimport;

import static org.junit.Assert.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore // ignored because these tests depend on network connection and themoviedb being up
public class MovieDbApiClientTest {
    
    private static final String SIN_CITY_MOVIE_ID = "187";
    private static final String JENS_DOT_NORDAHL_API_KEY = "5a96005d8e28d5f2e045b02540aff52a";
    MovieDbApiClient client;

    @Before
    public void setUp() {
        client = new MovieDbApiClient(JENS_DOT_NORDAHL_API_KEY);
    }
    
    @Test
    public void testGetMovie() {
        JSONArray json = client.getMovie(SIN_CITY_MOVIE_ID);
        assertNotNull(json);
        assertEquals(1, json.size());
        assertEquals("Sin City", ((JSONObject) json.get(0)).get("name"));
    }
}
