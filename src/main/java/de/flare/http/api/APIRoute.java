package de.flare.http.api;

import de.flare.http.RequestContext;

@FunctionalInterface
public interface APIRoute {

	void handle(RequestContext context);
}
