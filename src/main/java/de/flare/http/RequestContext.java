package de.flare.http;

import de.flare.database.model.User;
import org.hibernate.Session;
import spark.Request;
import spark.Response;

public class RequestContext {

	//region private members
	private Request request;
	private Response response;
	private Session session;
	private User user;
	private long executionStartTime = System.currentTimeMillis();

	public Request getRequest() {
		return request;
	}

	public RequestContext setRequest(Request request) {
		this.request = request;
		return this;
	}

	public Response getResponse() {
		return response;
	}

	public RequestContext setResponse(Response response) {
		this.response = response;
		return this;
	}

	public Session getSession() {
		return session;
	}

	public RequestContext setSession(Session session) {
		this.session = session;
		return this;
	}

	public User getUser() {
		return user;
	}

	public RequestContext setUser(User user) {
		this.user = user;
		return this;
	}

	public long getExecutionStartTime() {
		return executionStartTime;
	}
	//endregion
}
