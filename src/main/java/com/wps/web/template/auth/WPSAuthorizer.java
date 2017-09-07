package com.wps.web.template.auth;

import io.dropwizard.auth.Authorizer;

/**
 * I care only whether the user or role can access this area, we already know their password is correct. 
 * @author matthew <matthew.clifford-smith@wps-uk.com>
 *
 */
public class WPSAuthorizer implements Authorizer<User> {

	@Override
	public boolean authorize(User arg0, String arg1) {
		return true;
	}

}
