package com.neotechnology.cineasts.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neotechnology.cineasts.domain.Movie;

@RequestMapping(value = "/movie/**")
@Controller
public class MovieController {

	@RequestMapping(value = "{id}")
    public ModelAndView getMovieById(@PathVariable Long id) throws IOException {
		return new ModelAndView("movie","movie",new Movie());
	}
    
	@RequestMapping("all/{keyword}")
    public ModelAndView searchByKeyword(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,@PathVariable String keyword) throws IOException { 
    	return new ModelAndView("movies","movies",Arrays.asList(new Movie[]{new Movie(),new Movie(),new Movie()}));
	}

}
