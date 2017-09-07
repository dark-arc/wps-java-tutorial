package com.wps.web.template.auth;

import java.util.Optional;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

/**
 * My job is to check if the user is a valid user and the password is correct, 
 * I do not care if they can access this area
 * @author matthew <matthew.clifford-smith@mailbox.org>
 *
 */
public class WPSAuthenticator implements Authenticator<BasicCredentials, User>{

	@Override
	public Optional<User> authenticate(BasicCredentials arg0) throws AuthenticationException {
		User user = new User(arg0.getUsername(), arg0.getPassword());
		if(!arg0.getPassword().matches("unity")) {
			return Optional.empty();
		}
		return Optional.ofNullable(user);
	}

}
