package com.neotechnology.cineasts.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * This class prints root exceptions to console and also adds exception
 * information to the {@link ModelAndView}.
 * 
 * @author johanrask
 *
 */
public class ExceptionResolver extends SimpleMappingExceptionResolver {
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		System.out.println("============ EXCEPTION ==================");
		System.out.println("Handler: " + handler.getClass());
		ex.printStackTrace();
		System.out.println("============ EXCEPTION ==================");
		
		ModelAndView mav = super.resolveException(request, response, handler, ex);
		mav.addObject("reason",ex.getMessage());
		mav.addObject("exception",ex);
		return mav;
	}
}
