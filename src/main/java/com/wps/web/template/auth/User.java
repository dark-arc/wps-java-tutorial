package com.wps.web.template.auth;

import io.dropwizard.auth.PrincipalImpl;

public class User extends PrincipalImpl {

	public User(String name, String password) {
		super(name);
	}

}
