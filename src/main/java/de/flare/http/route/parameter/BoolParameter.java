package de.flare.http.route.parameter;

import com.sun.istack.internal.NotNull;

/**
 * This is the bool uri parameter implementation.
 */
public class BoolParameter extends StringParameter {

	//region ctor
	/**
	 * This constructor adds a constraint, so that the value must be a bool.
	 * @param name the uri parameter name
	 */
	public BoolParameter(@NotNull String name) {
		super(name);
		addConstraint(UriParameterConstraint.IS_BOOL);
	}
	//endregion
}
