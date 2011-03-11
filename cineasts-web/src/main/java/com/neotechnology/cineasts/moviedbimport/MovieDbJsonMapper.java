package com.neotechnology.cineasts.moviedbimport;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.neotechnology.cineasts.domain.Actor;
import com.neotechnology.cineasts.domain.Movie;
import com.neotechnology.cineasts.domain.Role;

@Component
public class MovieDbJsonMapper {

    public void mapToMovie(JSONArray movieJson, Movie movie) {
        try {
            JSONObject json = (JSONObject) movieJson.get(0);
            movie.setTitle((String) json.get("name"));
        }
        catch (Exception e) {
            throw new MovieDbException("Failed to map json for movie", e);
        }
    }

    public void mapToPerson(JSONArray personJson, Actor person) {
        try {
            JSONObject json = (JSONObject) personJson.get(0);
            person.setName((String) json.get("name"));
        }
        catch (Exception e) {
            throw new MovieDbException("Failed to map json for person", e);
        }
    }

    public Role mapToRole(String roleString) {
        if (roleString.equals("Actor")) {
            return Role.ACTOR;
        }
        if (roleString.equals("Director")) {
            return Role.DIRECTOR;
        }
        return null;
    }
}
