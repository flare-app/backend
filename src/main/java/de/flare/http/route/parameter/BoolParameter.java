package de.flare.http.route.parameter;

/**
 * This is the bool uri parameter implementation.
 */
public class BoolParameter extends StringParameter {

	//region ctor
	public BoolParameter() {
		addConstraint(UriParameterConstraint.IS_BOOL);
	}
	//endregion
}
