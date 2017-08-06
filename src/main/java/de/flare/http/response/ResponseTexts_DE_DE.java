package de.flare.http.response;

import de.flare.database.model.User;

public class ResponseTexts_DE_DE implements ResponseTexts {

	//region response texts

	@Override
	public String bodyIsInvalid() {
		return "Anfrage fehlerhaft.";
	}

	@Override
	public String accessIsDenied() {
		return "Zugriff verweigert.";
	}

	@Override
	public String serverError() {
		return "Es ist ein Fehler aufgetreten. Entschuldigung!";
	}

	@Override
	public String requestIsSuccessful() {
		return "Anfrage erfolgreich verarbeitet.";
	}

	@Override
	public String loginDetailsAreInvalid() {
		return "Login-Details ungültig.";
	}

	@Override
	public String newPasswordMustNotBeOldPassword() {
		return "Neues und altes Passwort dürfen nicht gleich sein.";
	}

	@Override
	public String userDoesAlreadyExist() {
		return "Nutzer existiert bereits.";
	}

	@Override
	public String welcome(User user) {
		return String.format("Willkommen %s %s.", user.getFirstName(), user.getLastName());
	}

	@Override
	public String userIsCreated() {
		return "Nutzer erfolgreich angelegt.";
	}

	@Override
	public String passwordIsChanged() {
		return "Password erfolgreich geändert.";
	}

	//endregion
}
