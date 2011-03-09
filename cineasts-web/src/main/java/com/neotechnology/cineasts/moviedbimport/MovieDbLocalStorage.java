package com.neotechnology.cineasts.moviedbimport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.commons.io.IOUtils;
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
        return (JSONArray) loadJsonValue(storageFile);
    }

    private Object loadJsonValue(File storageFile) {
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(storageFile));
            return JSONValue.parseWithException(reader);
        } catch (Exception e) {
            throw new MovieDbException("Failed to load JSON from storage for file "+storageFile.getPath(), e);
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

    public void storeMovie(String movieId, JSONArray movieJson) {
        File storageFile = fileForMovie(movieId);
        storeJsonValue(movieJson, storageFile);
    }

    private void storeJsonValue(Object json, File storageFile) {
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(storageFile));
            JSONValue.writeJSONString(json, writer);
            writer.close();
        } catch (Exception e) {
            throw new MovieDbException("Failed to store JSON to storage for file "+storageFile.getPath(), e);
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    public boolean hasPerson(String personId) {
        return fileForPerson(personId).exists();
    }

    private File fileForPerson(String personId) {
        return new File(storagePath, String.format("person_%s.json", personId));
    }

    public JSONArray loadPerson(String personId) {
        File storageFile = fileForPerson(personId);
        return (JSONArray) loadJsonValue(storageFile);
    }

    public void storePerson(String personId, JSONArray personJson) {
        File storageFile = fileForPerson(personId);
        storeJsonValue(personJson, storageFile);
    }    
}
