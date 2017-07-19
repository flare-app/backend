package de.flare.http.route.parameter;

import com.sun.istack.internal.NotNull;

/**
 * This is the int uri parameter implementation.
 */
public class IntParameter extends StringParameter {

	//region ctor
	/**
	 * This constructor adds a constraint, so that the value must be an integer.
	 * @param name the uri parameter name
	 */
	public IntParameter(@NotNull String name) {
		super(name);
		addConstraint(UriParameterConstraint.IS_INT);
	}

	/**
	 * This constructor adds constraints for the value to be in a certain range.
	 * @param name the uri parameter name
	 * @param minimum the range minimum. Hint: this is included in the range
	 * @param maximum the range maximum. Hint: this is included in the range
	 */
	public IntParameter(@NotNull String name, int minimum, int maximum) {
		super(name);
		addConstraint(UriParameterConstraint.IS_GREATER_THAN(minimum - 1));
		addConstraint(UriParameterConstraint.IS_SMALLER_THAN(maximum + 1));
	}
	//endregion
}
