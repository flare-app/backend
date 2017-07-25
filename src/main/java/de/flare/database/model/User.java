package de.flare.database.model;

import de.flare.database.definition.Authorization;
import de.flare.security.PasswordUtility;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "USER")
public class User extends DatabaseEntity {

	//region props
	@Column
	private String firstName = "";

	@Column
	private String lastName = "";

	@Column
	private String eMail = "";

	@Column
	private String passwordToken = "";

	@Column
	private boolean passwordExpired = true;

	@Column
	private String authenticationToken = UUID.randomUUID().toString() + "$" + System.currentTimeMillis();

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn
	private Unit unit;

	@ElementCollection(fetch = FetchType.LAZY)
	private List<String> qualifications = new ArrayList<>();

	@Column
	@Enumerated(EnumType.STRING)
	private Authorization authorization = Authorization.NONE;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn
	private Location defaultLocation = new Location();

	public String getFirstName() {
		return firstName;
	}

	public User setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String geteMail() {
		return eMail;
	}

	public User setEMail(String eMail) {
		this.eMail = eMail;
		return this;
	}

	public String getPasswordToken() {
		return passwordToken;
	}

	public User setPassword(String password) {
		passwordToken = PasswordUtility.getToken(password);
		return this;
	}

	public boolean isPasswordExpired() {
		return passwordExpired;
	}

	public User setPasswordExpired(boolean passwordExpired) {
		this.passwordExpired = passwordExpired;
		return this;
	}

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public Unit getUnit() {
		return unit;
	}

	public User setUnit(Unit unit) {
		this.unit = unit;
		return this;
	}

	public Authorization getAuthorization() {
		return authorization;
	}

	public User setAuthorization(Authorization authorization) {
		this.authorization = authorization;
		return this;
	}

	public List<String> getQualifications() {
		return qualifications;
	}

	public User setQualifications(List<String> qualifications) {
		this.qualifications = qualifications;
		return this;
	}

	public Location getDefaultLocation() {
		return defaultLocation;
	}

	public User setDefaultLocation(Location defaultLocation) {
		this.defaultLocation = defaultLocation;
		return this;
	}
	//endregion

	//region public methods
	public User updateAuthenticationToken() {
		authenticationToken = UUID.randomUUID().toString() + "$" + System.currentTimeMillis();
		return this;
	}
	//endregion
}
