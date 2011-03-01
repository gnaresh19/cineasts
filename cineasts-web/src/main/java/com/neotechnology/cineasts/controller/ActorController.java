package com.neotechnology.cineasts.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/actor/**")
@Controller
public class ActorController {

    @RequestMapping("{id}")
    public void getActor(@PathVariable Long id,ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	response.getWriter().write("echo:" + id);
    }
}
