package com.neotechnology.cineasts.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neotechnology.cineasts.domain.Account;
import com.neotechnology.cineasts.domain.Movie;
import com.neotechnology.cineasts.exception.AuthenticationException;
import com.neotechnology.cineasts.service.CineastsService;
import com.neotechnology.cineasts.util.AuthenticationUtils;

/**
 * <p>Controller managing movies</p>
 * 
 */
@RequestMapping(value = "/movie/**")
@Controller
public class MovieController {

    @Autowired
    CineastsService repository;

    /**
     * Displays the movie of the specified movie id
     */
	@RequestMapping(value = "{id}")
    public ModelAndView getMovieById(@PathVariable String id) throws IOException {
		
		Movie movie = repository.findMovieById(id);
		if(movie == null) {
			// 404?
			throw new RuntimeException("NO_SUCH_MOVIE:" + id);
		}
		return new ModelAndView("movie","movie",movie);
	}
    
	/**
	 * TODO - Implement to support keyword search
	 */
	@RequestMapping("all/{keyword}")
    public ModelAndView searchByKeyword(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,@PathVariable String keyword) { 
		return new ModelAndView("movies","movies",Arrays.asList(new Movie[]{new Movie(),new Movie()}));
	}
	
	/**
	 * Lists all movies.
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView listMovies() {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		for(Movie movie : repository.findAllMovies()) {
			movies.add(movie);
		}
		return new ModelAndView("movies","movies",movies);
	}

	/**
	 * Rates the specified movie and returns the movie view again.
	 */
	@RequestMapping(value = "rate",method = RequestMethod.POST)
	public ModelAndView rateMovie(@RequestParam("id") String movieId, @RequestParam("rating") int rating, @RequestParam("comment") String comment) {
		
		if(!AuthenticationUtils.isLoggedOn()) {
			throw new AuthenticationException("NOT_LOGGED_ON");
		}
		Movie movie = repository.findMovieById(movieId);
		Account account = repository.findAccountByUsername(AuthenticationUtils.getCurrentUsername());		
		account.rate(movie, comment, rating);
		
		return new ModelAndView("movie","movie",movie);
		
	}
	
}
