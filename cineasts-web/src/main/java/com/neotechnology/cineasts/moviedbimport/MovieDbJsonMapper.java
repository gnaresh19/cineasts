package com.neotechnology.cineasts.moviedbimport;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.neotechnology.cineasts.domain.Movie;

@Component
public class MovieDbJsonMapper {

    public Movie mapToMovie(JSONArray movieJson) {
        JSONObject json = (JSONObject) movieJson.get(0);
        Movie result = new Movie(((Long) json.get("id")).toString());
        result.setTitle((String) json.get("name"));
        return result;
    }

}
