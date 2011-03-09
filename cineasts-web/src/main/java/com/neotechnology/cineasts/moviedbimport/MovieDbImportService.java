package com.neotechnology.cineasts.moviedbimport;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neotechnology.cineasts.domain.Movie;
import com.neotechnology.cineasts.service.CineastsService;

@Component
public class MovieDbImportService {

    @Autowired
    CineastsService cineastsService;    
    @Autowired
    MovieDbApiClient client;
    @Autowired
    MovieDbJsonMapper movieDbJsonMapper;
    @Autowired
    MovieDbLocalStorage localStorage;
    
    public void importMovie(String movieId) {        
        JSONArray movieJson;
        if (localStorage.hasMovie(movieId)) {
            movieJson = localStorage.loadMovie(movieId);
        }
        else {
            movieJson = client.getMovie(movieId);
            localStorage.storeMovie(movieId, movieJson);
        }
        
        Movie movie = movieDbJsonMapper.mapToMovie(movieJson);
        cineastsService.save(movie);
    }
}
