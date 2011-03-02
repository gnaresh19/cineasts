package com.neotechnology.cineasts.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.neotechnology.cineasts.domain.Account;
import com.neotechnology.cineasts.util.WebUtils;

/**
 * Controller for registering, viewing and updating user accounts.
 * Login is done separately with spring-security.
 * <br/><br/>
 * 
 * TODO - We have no spring-security support for neo so once we can register
 *        accounts here we still have to update the spring security config
 *        with new users until we can integrate with neo.
 * 
 */
@RequestMapping("/account/**")
@Controller

public class AccountController {
	
	
	static String username = "johan";
	
	/**
	 * Tmp method for simple test from the browser
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/getregister", method = RequestMethod.GET)
	public ModelAndView getregister(Account account) {
		return register(account);
	}
	
	/** 
	 * NOT USED - We are currently dependent on external spring security setup
	 * We might have to build a neo module for this
	 *
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(Account account) {
		System.out.println(account);
		
		if(!account.isValid()) {
			throw new RuntimeException("Invalid credentials");
		}
		
		//1. Verify with repository that the userid does not exist
		//2. Save account
		
		return new ModelAndView("account.created","account",account);
	}

	
	/** 
	 * NOT USED - We are currently dependent on external spring security setup
	 *
	 */
	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password) {
		
		System.out.println(username + " " + password);
		
		Account account = null;
		if(username.equals(AccountController.username)) {
			account = new Account(username, password, "email");
			WebUtils.getSession().setAttribute("account", account);
		} else {
			System.out.println("NOT LOGGED IN");
			throw new RuntimeException("Invalid login");
		}
		
		// 1. verify and fetch account from repository
		//Account account = repo.findByUsername(username);
		
		return new ModelAndView("account.user.home","account",account);
	}
	
	
	/**
	 * Fetches the currently logged in useraccount from repository and
	 * returns in in the {@link ModelAndView}.
	 */
	@RequestMapping(value = "/view")
	public ModelAndView view() {
		
		
		System.out.println("is logged on=" + WebUtils.isLoggedOn());
		if(!WebUtils.isLoggedOn()) {
			throw new RuntimeException("Not logged on");
		}

		// Username fetched here can be used to get the account
		Account account = (Account)WebUtils.getSessionAttr("account");
		
		//		// 1. verify and fetch account from repository
		//Account account = repo.findByUsername(username);
	
		return new ModelAndView("account.user.home","account",account);
	}
	
	
}
