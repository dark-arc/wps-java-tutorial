package com.wps.web.template.web;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@PermitAll
@Path("/add")
@Produces(MediaType.APPLICATION_JSON)
public class Add {
	public Add() {
		
	}
	
	@GET
	public Response addNumbers(@QueryParam("a") int a, @QueryParam("b") int b) {
		AdditionQueryResult result = new AdditionQueryResult();
		result.result = a+b;
		return Response.ok(result).build();
	}
	
	@POST
	public Response addNumbers(AdditionQueryParameters p) {
		AdditionQueryResult result = new AdditionQueryResult();
		result.result = p.a + p.b;
		return Response.ok(result).build();
	}
}
