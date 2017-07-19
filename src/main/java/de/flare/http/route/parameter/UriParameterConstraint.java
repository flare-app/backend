package de.flare.http.route.parameter;

import com.sun.istack.internal.NotNull;

import java.util.function.Function;

/**
 * This interface offers the method to validate a uri parameter value.
 */
public interface UriParameterConstraint extends Function<String, Boolean> {

	//region convenience constraints
	UriParameterConstraint NOT_NULL = new UriParameterConstraint() {
		@Override
		@NotNull
		public String getDescription() {
			return "input must not be null";
		}

		@Override
		public Boolean apply(String s) {
			return s != null;
		}
	};

	UriParameterConstraint NOT_EMPTY = new UriParameterConstraint() {
		@Override
		@NotNull
		public String getDescription() {
			return "input most not be empty";
		}

		@Override
		public Boolean apply(String s) {
			try {
				return !s.isEmpty();
			} catch (Exception e) {
				return false;
			}
		}
	};

	UriParameterConstraint NOT_NULL_NOT_EMPTY = new UriParameterConstraint() {
		@Override
		@NotNull
		public String getDescription() {
			return "input must not be null and must not be empty";
		}

		@Override
		public Boolean apply(String s) {
			return NOT_NULL.apply(s) && NOT_EMPTY.apply(s);
		}
	};

	UriParameterConstraint IS_INT = new UriParameterConstraint() {
		@Override
		@NotNull
		public String getDescription() {
			return "input must be an integer";
		}

		@Override
		public Boolean apply(String s) {
			try {
				Integer.parseInt(s);
				return true;
			} catch (Exception e) {
				// suppress exception
				return false;
			}
		}
	};

	UriParameterConstraint IS_LONG = new UriParameterConstraint() {
		@Override
		@NotNull
		public String getDescription() {
			return "input must be a long";
		}

		@Override
		public Boolean apply(String s) {
			try {
				Long.parseLong(s);
				return true;
			} catch (Exception e) {
				// suppress exception
				return false;
			}
		}
	};

	UriParameterConstraint IS_BOOL = new UriParameterConstraint() {
		@Override
		@NotNull
		public String getDescription() {
			return "input must be a bool (true, false)";
		}

		@Override
		public Boolean apply(String s) {
			try {
				return s.equalsIgnoreCase("false") || s.equalsIgnoreCase("true");
			} catch (Exception e) {
				return false;
			}
		}
	};

	static UriParameterConstraint IS_GREATER_THAN(int minimum) {
		return new UriParameterConstraint() {
			@Override
			@NotNull
			public String getDescription() {
				return "input must be greater than " + minimum;
			}

			@Override
			public Boolean apply(String s) {
				try {
					return (Integer.parseInt(s) > minimum);
				} catch (Exception e) {
					return false;
				}
			}
		};
	}

	static UriParameterConstraint IS_GREATER_THAN(long minimum) {
		return new UriParameterConstraint() {
			@Override
			@NotNull
			public String getDescription() {
				return "input must be greater than " + minimum;
			}

			@Override
			public Boolean apply(String s) {
				try {
					return (Long.parseLong(s) > minimum);
				} catch (Exception e) {
					return false;
				}
			}
		};
	}

	static UriParameterConstraint IS_SMALLER_THAN(int maximum) {
		return new UriParameterConstraint() {
			@Override
			@NotNull
			public String getDescription() {
				return "input must be smaller than " + maximum;
			}

			@Override
			public Boolean apply(String s) {
				try {
					return (Integer.parseInt(s) < maximum);
				} catch (Exception e) {
					return false;
				}
			}
		};
	}

	static UriParameterConstraint IS_SMALLER_THAN(long maximum) {
		return new UriParameterConstraint() {
			@Override
			@NotNull
			public String getDescription() {
				return "input must be smaller than " + maximum;
			}

			@Override
			public Boolean apply(String s) {
				try {
					return (Long.parseLong(s) < maximum);
				} catch (Exception e) {
					return false;
				}
			}
		};
	}
	//endregion

	/**
	 * This method returns the constraint description.
	 * @return the description
	 */
	@NotNull
	String getDescription();
}
