package com.neotechnology.cineasts.util;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class WebUtils {
	
	
	public static boolean isLoggedOn() {
		return getSession().getAttribute("account") != null;
	}
	
	public static Object getSessionAttr(String key) {
		return getSession().getAttribute(key);
	}
	
	public static void setSessionAttr(String key,Object value) {
		getSession().setAttribute(key,value);
	}
	
	public static String getCurrentUser() {
		return auth().getName();
	}
	
	
	private static Authentication auth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public static HttpSession getSession() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true); // true == allow create
	}

}
