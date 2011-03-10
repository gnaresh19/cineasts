package com.neotechnology.cineasts.moviedbimport;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.neotechnology.cineasts.domain.Actor;
import com.neotechnology.cineasts.domain.Movie;
import com.neotechnology.cineasts.domain.Role;

@Component
public class MovieDbJsonMapper {

    public Movie mapToMovie(JSONArray movieJson) {
        JSONObject json = (JSONObject) movieJson.get(0);
        Movie result = new Movie(((Long) json.get("id")).toString());
        result.setTitle((String) json.get("name"));
        return result;
    }

    public Actor mapToPerson(JSONArray personJson) {
        JSONObject json = (JSONObject) personJson.get(0);
        Actor result = new Actor(((Long) json.get("id")).toString(), (String) json.get("name"));
        return result;
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
