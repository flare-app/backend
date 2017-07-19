package de.flare.http.route.parameter;

import com.sun.istack.internal.NotNull;

/**
 * This exception is thrown, if at least one constraint for a given uri parameter failed.
 */
public class UriParameterInvalidException extends Exception {

	//region ctor
	public UriParameterInvalidException(@NotNull UriParameter parameter, @NotNull UriParameterConstraint constraint) {
		super(String.format("constraint for parameter '%s' failed: %s",
				parameter.getName(),
				constraint.getDescription()));
	}
	//endregion
}
