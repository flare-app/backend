package de.flare.application;

import de.flare.database.DatabaseAccess;
import de.flare.database.definition.Authorization;
import de.flare.database.model.Location;
import de.flare.database.model.Unit;
import de.flare.database.model.User;
import de.flare.http.WebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

	//region private const
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	//endregion

	public static void main(String[] args) {
		if (!DatabaseAccess.establishConnection()) {
			logger.error("cannot connect to database");
			return;
		}

		if (DatabaseAccess.getUsers(Authorization.FLARE_ADMINISTRATOR).isEmpty()) {
			User flareAdministrator = new User()
					.setAuthorization(Authorization.FLARE_ADMINISTRATOR)
					.setFirstName("Administrator")
					.setLastName("")
					.setPassword("root")
					.setEMail("administrator@flare.de")
					.setPasswordExpired(true);

			User unitAdministrator = new User()
					.setAuthorization(Authorization.UNIT_ADMINISTRATOR)
					.setFirstName("John")
					.setLastName("Doe")
					.setPassword("123456")
					.setEMail("john.doe@example.com")
					.setPasswordExpired(true)
					.setUnit(new Unit()
						.setName("Best Unit EUW")
						.setLocation(new Location()
							.setCity("Potsdam")
							.setLatitude(1.f)
							.setLongitude(1.f)))
					.setDefaultLocation(new Location()
						.setCity("Potsdam")
						.setLatitude(1.1f)
						.setLongitude(1.1f));

			if (!DatabaseAccess.save(flareAdministrator, unitAdministrator)) {
				logger.error("cannot create flare administrator");
				return;
			}
		}

		if (!WebServer.listen()) {
			logger.error("cannot create web server");
			return;
		}
	}
}
