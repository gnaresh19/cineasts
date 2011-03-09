package com.neotechnology.cineasts.moviedbimport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

public class MovieDbLocalStorage {

    private String storagePath;

    public MovieDbLocalStorage(String storagePath) {
        this.storagePath = storagePath;
        ensureStorageDirectoryExists();
    }

    private void ensureStorageDirectoryExists() {
        File storageDirectory = new File(storagePath);
        if (!storageDirectory.isDirectory()) {
            if (!storageDirectory.mkdir()) {
                throw new RuntimeException("Failed to create storage dir");
            }
        }
    }

    public boolean hasMovie(String movieId) {
        return fileForMovie(movieId).exists();
    }

    private File fileForMovie(String movieId) {
        return new File(storagePath, String.format("movie_%s.json", movieId));
    }

    public JSONArray loadMovie(String movieId) {
        File storageFile = fileForMovie(movieId);
        try {
            return (JSONArray) JSONValue.parseWithException(new InputStreamReader(new FileInputStream(storageFile)));
        } catch (Exception e) {
            throw new MovieDbException("Failed to load JSON from storage for movie "+movieId, e);
        }
    }

    public void storeMovie(String movieId, JSONArray movieJson) {
        File storageFile = fileForMovie(movieId);
        try {
            JSONValue.writeJSONString(movieJson, new OutputStreamWriter(new FileOutputStream(storageFile)));
        } catch (Exception e) {
            throw new MovieDbException("Failed to store JSON to storage for movie "+movieId, e);
        }
    }    
}
