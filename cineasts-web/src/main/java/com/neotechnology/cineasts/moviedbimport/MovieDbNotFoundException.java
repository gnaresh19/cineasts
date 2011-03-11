package com.neotechnology.cineasts.moviedbimport;

public class MovieDbNotFoundException extends MovieDbException {

    private static final long serialVersionUID = 1L;

    public MovieDbNotFoundException(String message) {
        super(message);
    }
}
