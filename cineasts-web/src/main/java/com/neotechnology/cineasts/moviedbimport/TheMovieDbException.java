package com.neotechnology.cineasts.moviedbimport;

public class TheMovieDbException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TheMovieDbException(String message) {
        super(message);
    }
    
    public TheMovieDbException(String message, Throwable cause) {
        super(message, cause);
    }
}
