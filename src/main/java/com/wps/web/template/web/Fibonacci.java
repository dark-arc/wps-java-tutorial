package com.wps.web.template.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@PermitAll
@Path("/fib")
@Produces(MediaType.APPLICATION_JSON)
public class Fibonacci {
	@GET
	public Response fib(@QueryParam("to") int to) {
		List<BigInteger> numbers = new ArrayList<BigInteger>();
		
		numbers.add(BigInteger.valueOf(0));
		numbers.add(BigInteger.valueOf(1));
		
		for(int i = 2; i <= to; i++) {
			BigInteger n1 = numbers.get(i-1);
			BigInteger n2 = numbers.get(i-2);
			
			numbers.add(n1.add(n2));
		}
		
		return Response.ok(numbers).build();
	}
}
