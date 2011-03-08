package com.neotechnology.cineasts.moviedbimport;

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
        return getUrlAsJson(movieUrl);
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
                throw new TheMovieDbException("Received data are not a JSON array");
            }       
        }
        catch (Exception e) {
            throw new TheMovieDbException("Failed to get data from " + url + " - " + e.getMessage(), e);
        }
        finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    private String buildMovieUrl(String movieId) {
        return String.format("%s2.1/Movie.getInfo/en/json/%s/%s", baseUrl, apiKey, movieId);
    }
}
