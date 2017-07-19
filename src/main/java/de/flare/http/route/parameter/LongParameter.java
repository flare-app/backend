package de.flare.http.route.parameter;

/**
 * This is the long uri parameter implementation.
 */
public class LongParameter extends StringParameter {

	//region ctor
	public LongParameter() {
		addConstraint(UriParameterConstraint.IS_LONG);
	}

	public LongParameter(long minimum, long maximum) {
		addConstraint(UriParameterConstraint.IS_GREATER_THAN(minimum - 1));
		addConstraint(UriParameterConstraint.IS_SMALLER_THAN(maximum + 1));
	}
	//endregion
}
