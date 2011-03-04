package com.neotechnology.cineasts.util;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.neotechnology.cineasts.domain.Account;

public class AuthenticationUtils {
	
	
	public static boolean isLoggedOn() {
		return getSession().getAttribute("account") != null;
	}
	
	public static Object getSessionAttr(String key) {
		return getSession().getAttribute(key);
	}
	
	public static void setSessionAttr(String key,Object value) {
		getSession().setAttribute(key,value);
	}
	
	public static String getCurrentUsername() {
		Account account = (Account)getSessionAttr("account");
		return account.getUsername();
	}
	
	
	private static Authentication auth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public static HttpSession getSession() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true); // true == allow create
	}

}
