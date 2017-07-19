package de.flare.http.route.parameter;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * This is the string uri parameter implementation.
 */
public class StringParameter implements UriParameter {

	//region private members
	private String name = "parameter";
	private Collection<UriParameterConstraint> constraints = new ArrayList<>();
	//endregion

	//region ctor

	/**
	 * This constructor initializes all members and sets the name of the uri parameter.
	 * @param name the uri parameter name
	 */
	public StringParameter(@NotNull String name) {
		setName(name);
		this.addConstraint(UriParameterConstraint.NOT_NULL_NOT_EMPTY);
	}
	//endregion

	//region uri parameter
	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public UriParameter setName(@NotNull String name) {
		this.name = name;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public String getUriName() {
		return UriParameter.toUriName(getName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public Collection<UriParameterConstraint> getConstraints() {
		return Collections.unmodifiableCollection(constraints);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@NotNull
	public UriParameter addConstraint(@NotNull UriParameterConstraint constraint) {
		constraints.add(constraint);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validate(String value) throws UriParameterInvalidException {
		for (UriParameterConstraint constraint : constraints) {
			if (!constraint.apply(value)) {
				throw new UriParameterInvalidException(this, constraint);
			}
		}

		return true;
	}
	//endregion
}
