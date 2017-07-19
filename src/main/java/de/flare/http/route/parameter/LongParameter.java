package de.flare.http.route.parameter;

import com.sun.istack.internal.NotNull;

/**
 * This is the long uri parameter implementation.
 */
public class LongParameter extends StringParameter {

	//region ctor
	/**
	 * This constructor adds a constraint, so that the value must be a long.
	 * @param name the uri parameter name
	 */
	public LongParameter(@NotNull String name) {
		super(name);
		addConstraint(UriParameterConstraint.IS_LONG);
	}

	/**
	 * This constructor adds constraints for the value to be in a certain range.
	 * @param name the uri parameter name
	 * @param minimum the range minimum. Hint: this is included in the range
	 * @param maximum the range maximum. Hint: this is included in the range
	 */
	public LongParameter(@NotNull String name, long minimum, long maximum) {
		super(name);
		addConstraint(UriParameterConstraint.IS_GREATER_THAN(minimum - 1));
		addConstraint(UriParameterConstraint.IS_SMALLER_THAN(maximum + 1));
	}
	//endregion
}
