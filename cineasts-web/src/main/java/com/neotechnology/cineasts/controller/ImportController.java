package com.neotechnology.cineasts.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neotechnology.cineasts.domain.Movie;
import com.neotechnology.cineasts.moviedbimport.MovieDbException;
import com.neotechnology.cineasts.moviedbimport.MovieDbImportService;
import com.neotechnology.cineasts.moviedbimport.MovieDbNotFoundException;

@Controller
public class ImportController {

    private static final Logger logger = LoggerFactory.getLogger(ImportController.class);

    @Autowired
    MovieDbImportService importService;

    @RequestMapping("/import")
    public String showImportPage() {
        return "import.form";
    }

    @RequestMapping(value = "/import/movie", method = RequestMethod.POST)
    public ModelAndView importMovie(String movieIds) {
        List<String> idsToImport = parseMovieIds(movieIds);
        Map<String, String> importResult = new HashMap<String, String>();
        
        for (String movieId: idsToImport) {
            try {
                Movie movie = importService.importMovie(movieId);
                importResult.put(movieId, movie.getTitle() + " (successful)");
            }
            catch (MovieDbNotFoundException e) {
                logger.info("Movie {} not found at import", movieId);
                importResult.put(movieId, "Not found");                    
                
            }
            catch (MovieDbException e) {
                logger.warn("Failed to import movie {}, continuing.", movieId);
                logger.warn("Exception during movie import", e);
                importResult.put(movieId, "Failed");
            }
        }
        
        return new ModelAndView("import.done", "importResult", importResult);
    }

    private List<String> parseMovieIds(String movieIds) {
        List<String> result = new ArrayList<String>();
        for (String commaElem: movieIds.split(",")) {
            if (commaElem.contains("-")) {
                String[] dashElements = commaElem.split("-");
                if (dashElements.length != 2) {
                    throw new RuntimeException("Invalid movie id range format in "+commaElem);
                }
                try {
                    int firstMovieId = Integer.parseInt(dashElements[0].trim());
                    int lastMovieId = Integer.parseInt(dashElements[1].trim());
                    for (int movieId=firstMovieId; movieId<=lastMovieId; movieId++) {
                        result.add(Integer.toString(movieId));
                    }
                }
                catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid movie id range format in "+commaElem);                    
                }
            } 
            else {
                if (commaElem.trim().length() > 0) {
                    result.add(commaElem.trim());
                }
                else {
                    throw new RuntimeException("Invalid movie id list: "+movieIds);                                        
                }
            }
        }
        return result;
    }
}
