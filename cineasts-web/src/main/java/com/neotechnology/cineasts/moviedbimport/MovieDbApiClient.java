package com.neotechnology.cineasts.moviedbimport;

import static com.neotechnology.cineasts.util.JXPathUtils.jxString;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

public class MovieDbApiClient {
    
    private String baseUrl = "http://api.themoviedb.org/"; 
    private String apiKey;
    private HttpClient httpClient;

    public MovieDbApiClient(String apiKey) {
        this.apiKey = apiKey;

        BasicHttpParams httpParams = new BasicHttpParams();
        httpParams.setBooleanParameter(ClientPNames.HANDLE_REDIRECTS, false);
        httpClient = new DefaultHttpClient(httpParams);
    }

    public JSONArray getMovie(String movieId) {
        String movieUrl = buildMovieUrl(movieId);
        JSONArray json = getUrlAsJson(movieUrl);
        if (notFound(json)) {
            throw new MovieDbNotFoundException("Movie not found");
        }        
        return json;
    }
    
    // The movie db API signals movie not found by returning an array containing 
    // the string "Nothing found."
    private boolean notFound(JSONArray json) {
        return jxString(json, ".[1]").equals("Nothing found."); 
    }

    private JSONArray getUrlAsJson(String url) {
        HttpGet httpGet = new HttpGet(url);
        InputStream inputStream = null;
        try {
            HttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                throw new RuntimeException("Status code nok OK: " + statusCode);
            }
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
            InputStreamReader responseReader = new InputStreamReader(inputStream);
            Object parsedObject = JSONValue.parseWithException(responseReader);
            if (parsedObject instanceof JSONArray) {
                return (JSONArray) parsedObject;
            }
            else {
                throw new MovieDbException("Received data are not a JSON array");
            }       
        }
        catch (Exception e) {
            throw new MovieDbException("Failed to get data from " + url + " - " + e.getMessage(), e);
        }
        finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    private String buildMovieUrl(String movieId) {
        return String.format("%s2.1/Movie.getInfo/en/json/%s/%s", baseUrl, apiKey, movieId);
    }

    public JSONArray getPerson(String personId) {
        String personUrl = buildPersonUrl(personId);
        return getUrlAsJson(personUrl);
    }

    private String buildPersonUrl(String personId) {
        return String.format("%s2.1/Person.getInfo/en/json/%s/%s", baseUrl, apiKey, personId);
    }
}
