package de.flare.http.response;

import de.flare.database.model.User;

public class ResponseTexts_EN_US implements ResponseTexts {

	//region response texts

	@Override
	public String bodyIsInvalid() {
		return "Request invalid.";
	}

	@Override
	public String accessIsDenied() {
		return "Access denied.";
	}

	@Override
	public String serverError() {
		return "An internal server error occurred. Sorry!";
	}

	@Override
	public String requestIsSuccessful() {
		return "Request has been processed successfully.";
	}

	@Override
	public String loginDetailsAreInvalid() {
		return "Login details invalid.";
	}

	@Override
	public String newPasswordMustNotBeOldPassword() {
		return "New password must not be the old password.";
	}

	@Override
	public String userDoesAlreadyExist() {
		return "User already exists.";
	}

	@Override
	public String welcome(User user) {
		return String.format("Welcome %s %s.", user.getFirstName(), user.getLastName());
	}

	@Override
	public String userIsCreated() {
		return "User created successfully.";
	}

	@Override
	public String passwordIsChanged() {
		return "Password updated successfully.";
	}

	//endregion
}
