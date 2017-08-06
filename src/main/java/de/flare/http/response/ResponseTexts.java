package de.flare.http.response;

import de.flare.database.model.User;

public interface ResponseTexts {

	//---
	//region general

	//region error
	String bodyIsInvalid();

	String accessIsDenied();

	String serverError();
	//endregion

	//region success
	String requestIsSuccessful();
	//endregion

	//endregion

	//---
	//region user

	//region error
	String loginDetailsAreInvalid();

	String newPasswordMustNotBeOldPassword();

	String userDoesAlreadyExist();
	//endregion

	//region success
	String welcome(User user);

	String userIsCreated();

	String passwordIsChanged();
	//endregion

	//endregion
}
