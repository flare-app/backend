package de.flare.http.route.execution;

import com.sun.istack.internal.NotNull;

/**
 * This interface offers a method to handle rest requests.
 */
@FunctionalInterface
public interface RestRouteHandler {

	/**
	 * This method handles the execution of a specific rest route.
	 * @param context the context of this execution
	 */
	void handle(@NotNull RestRouteExecutionContext context);
}
