package com.neotechnology.cineasts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neotechnology.cineasts.repository.SDGRepository;

@Controller
public class PopulateController {

    @Autowired
    SDGRepository repository;

    @RequestMapping("/populate")
    @Transactional
    public ModelAndView populate() {
        try {
            repository.populateWithSomeTestData();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("actor", "actor", null);
    }
}
