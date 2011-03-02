package com.neotechnology.cineasts.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neotechnology.cineasts.domain.Actor;
import com.neotechnology.cineasts.service.CineastsService;

@RequestMapping("/actor/**")
@Controller
public class ActorController {
    
    @Autowired
    CineastsService repository;

    @RequestMapping("{id}")
    public ModelAndView getActor(@PathVariable String id,ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
        Actor actor = repository.findActorById(id);
        return new ModelAndView("actor","actor",actor);
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
