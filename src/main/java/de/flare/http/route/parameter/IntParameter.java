package de.flare.http.route.parameter;

/**
 * This is the int uri parameter implementation.
 */
public class IntParameter extends StringParameter {

	//region ctor
	public IntParameter() {
		addConstraint(UriParameterConstraint.IS_INT);
	}

	public IntParameter(int minimum, int maximum) {
		addConstraint(UriParameterConstraint.IS_GREATER_THAN(minimum - 1));
		addConstraint(UriParameterConstraint.IS_SMALLER_THAN(maximum + 1));
	}
	//endregion
}
