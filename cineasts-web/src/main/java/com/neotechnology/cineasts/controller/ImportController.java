package com.neotechnology.cineasts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neotechnology.cineasts.moviedbimport.MovieDbImportService;

@Controller
public class ImportController {

    @Autowired
    MovieDbImportService importService;

    @RequestMapping("/import")
    public String showImportPage() {
        return "import.form";
    }

    @RequestMapping(value = "/import/movie", method = RequestMethod.POST)
    public String importMovie(String movieId) {
        importService.importMovie(movieId);
        return "import.success";
    }
}
