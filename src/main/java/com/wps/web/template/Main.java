package com.wps.web.template;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.wps.web.template.auth.User;
import com.wps.web.template.auth.WPSAuthenticator;
import com.wps.web.template.auth.WPSAuthorizer;
import com.wps.web.template.web.Add;
import com.wps.web.template.web.Fibonacci;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * @author Matthew <matthew.clifford-smith@mailbox.org>
 */
public class Main extends Application<AppConfiguration> {
	public static void main(String[] args) throws Exception {
		new Main().run(args);		
	}
	
	@Override
	public void initialize(Bootstrap<AppConfiguration> bootstrap) {
		/* This will format dates we output as ISO Dates instead of millis */
		JodaModule jm = new JodaModule();
		jm.addSerializer(DateTime.class, 
				new DateTimeSerializer(
						new JacksonJodaDateFormat(ISODateTimeFormat.dateTimeNoMillis())));
		bootstrap.getObjectMapper().registerModule(jm);
		bootstrap.getObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}
	
	@Override
	public void run(AppConfiguration configuration, Environment env) {
		final Add add = new Add();
		final Fibonacci fib = new Fibonacci();
		
		env.jersey().register(new AuthDynamicFeature(
				new BasicCredentialAuthFilter.Builder<User>()
				.setAuthenticator(new WPSAuthenticator())
				.setAuthorizer(new WPSAuthorizer())
				.setRealm("WPS")
				.buildAuthFilter())
				);
		env.jersey().register(RolesAllowedDynamicFeature.class);
		env.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
		
		env.jersey().register(add);
		env.jersey().register(fib);
	}
}
