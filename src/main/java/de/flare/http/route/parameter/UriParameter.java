package de.flare.http.route.parameter;

import com.sun.istack.internal.NotNull;
import de.flare.http.route.parameter.exception.UriParameterInvalidException;

import java.util.Collection;

/**
 * This interface contains data for a uri parameter.
 */
public interface UriParameter {

	//region static methods

	/**
	 * This method converts a parameter name to a uri parameter name, which can be placed inside a uri.
	 * @param name the name to convert
	 * @return the converted uri parameter name
	 */
	@NotNull
	static String toUriName(@NotNull String name) {
		return String.format("{%s}", name);
	}
	//endregion

	/**
	 * This method returns the name of the parameter.
	 * @return the name of the parameter
	 */
	@NotNull
	String getName();

	/**
	 * This method sets the name of the parameter.
	 * @param name the name to set
	 * @return this uri parameter
	 */
	@NotNull
	UriParameter setName(@NotNull String name);

	/**
	 * This method returns the parameter name in the uri format.
	 * @return the parameter name in the uri format
	 */
	@NotNull
	String getUriName();

	/**
	 * This method returns the set of constraints for this parameter.
	 * @return the set of constraints
	 */
	@NotNull
	Collection<UriParameterConstraint> getConstraints();

	/**
	 * This method adds a new constraint for this parameter.
	 * @param constraint the constraint to add
	 * @return this uri parameter
	 */
	@NotNull
	UriParameter addConstraint(@NotNull UriParameterConstraint constraint);

	/**
	 * This method validates a given input, using the defined constraints.
	 * @param value the value for the parameter
	 * @return true, if the validation was successful
	 * @throws UriParameterInvalidException thrown, if at least one constraint failed
	 */
	boolean validate(String value) throws UriParameterInvalidException;
}
