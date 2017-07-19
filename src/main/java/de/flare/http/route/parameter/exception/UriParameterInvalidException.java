package de.flare.http.route.parameter.exception;

import com.sun.istack.internal.NotNull;
import de.flare.http.route.parameter.UriParameter;
import de.flare.http.route.parameter.UriParameterConstraint;

/**
 * This exception is thrown, if at least one constraint for a given uri parameter failed.
 */
public class UriParameterInvalidException extends Exception {

	//region ctor

	/**
	 * This constructor initializes the exception.
	 * @param parameter the parameter, which caused the exception
	 * @param constraint the constraint, which failed
	 */
	public UriParameterInvalidException(@NotNull UriParameter parameter, @NotNull UriParameterConstraint constraint) {
		super(String.format("constraint for parameter '%s' failed: %s",
				parameter.getName(),
				constraint.getDescription()));
	}
	//endregion
}
