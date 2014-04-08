/**
 * 
 */
package com.webcontext.apps.gk2.services.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

import com.webcontext.apps.gk2.persist.GameService;

/**
 * Main application class to serve REST web service. All service are declared in
 * this application class.
 * 
 * @author Frederic Delorme<frederic.delorme@gmail.com>
 * 
 */
@Path("/rest")
public class GamesApplication extends Application {

	/*
	 * (non-Javadoc)
	 * @see javax.ws.rs.core.Application#getSingletons()
	 */
	@Override
	public Set<Object> getSingletons() {
		Set<Object> services = new HashSet<Object>();

		GameService gameService = new GameService();

		services.add(gameService);

		return services;

	}
}
