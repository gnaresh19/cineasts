package com.neotechnology.cineasts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.graph.neo4j.support.GraphDatabaseContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neotechnology.cineasts.domain.Account;
import com.neotechnology.cineasts.exception.AuthenticationException;
import com.neotechnology.cineasts.service.CineastsService;
import com.neotechnology.cineasts.util.AuthenticationUtils;

/**
 * Controller for registering, viewing and updating user accounts. Login is done
 * separately with spring-security. <br/>
 * <br/>
 * 
 * TODO - We have no spring-security support for neo so once we can register
 * accounts here we still have to update the spring security config with new
 * users until we can integrate with neo.
 * 
 */
@RequestMapping("/account/**")
@Controller
public class AccountController {

	@Autowired
	CineastsService repository;
	 
	/**
	 * Tmp method for simple test from the browser
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/getregister", method = RequestMethod.GET)
	public ModelAndView getregister(Account account) {
		return register(account);
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationPage() {
		return "account.register";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage() {
		return "account.login";
	}

	/**
	 * Registers a new Account.
	 * 
	 * @param account
	 *            - new Account
	 * @return {@link ModelAndView} to account.login if successful,
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(Account account) {
		
		System.out.println(" => register() " + account);
		if (!account.isValid()) {
			ModelAndView mav = new ModelAndView("account.register", "account",
					account);
			mav.addObject("reason", "INVALID_CREDENTIALS");
			return mav;
		}

		Account savedAccount = repository.findAccountByUsername(account
				.getUsername());
		if (savedAccount != null) {
			System.out.println("register, found account: " + savedAccount);
			ModelAndView mav = new ModelAndView("account.register", "account",
					account);
			mav.addObject("reason", "ALREADY_EXIST");
			return mav;
		}
		
		// We have to explicity save the account
		repository.save(account);
		return new ModelAndView("account.login", "account", account);
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("username") String username,
			@RequestParam("password") String password) {

		System.out.println(username + " " + password);

		try {
			Account account = repository.findAccountByUsername(username);
			verifyAccount(account,username,password);
			AuthenticationUtils.setSessionAttr("account", account);
			return new ModelAndView("account.user.home", "account", account);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		AuthenticationUtils.setSessionAttr("account", null);
		return "index";
	}
	
	private void verifyAccount(Account account, String username, String password) {
		if(account == null) {
			throw new AuthenticationException("NO_SUCH_USER");
		}else if(!password.equals(account.getPassword())) {
			throw new AuthenticationException("INVALID_PASSWORD");
		}
		
	}

	/**
	 * Fetches the currently logged in useraccount from repository and returns
	 * in in the {@link ModelAndView}.
	 */
	@RequestMapping(value = "/view")
	public ModelAndView view() {
		Account account = (Account) AuthenticationUtils.getSessionAttr("account");
		if (account == null) {
			throw new AuthenticationException("NOT_LOGGED_ON");
		}
		return new ModelAndView("account.user.home", "account", account);
	}

}
