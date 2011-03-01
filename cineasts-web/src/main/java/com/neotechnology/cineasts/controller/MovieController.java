package com.neotechnology.cineasts.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/movie/**")
@Controller
public class MovieController {

	@RequestMapping(value = "{id}")
    public void getMovieById(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,@PathVariable Long id) throws IOException {
		response.getWriter().write("echo:" + id);
	}
    
	@RequestMapping("all/{keyword}")
    public void searchByKeyword(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,@PathVariable String keyword) throws IOException {
    	response.getWriter().write("echo:" + keyword);
    }

}
