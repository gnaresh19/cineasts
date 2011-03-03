package com.neotechnology.cineasts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neotechnology.cineasts.service.SpringDataGraphCineastsService;

@Controller
public class PopulateController {

    @Autowired
    SpringDataGraphCineastsService repository;

    @RequestMapping("/populate")
    public String populate() {
    	repository.populateWithSomeTestData();

        return "index";
    }
}
