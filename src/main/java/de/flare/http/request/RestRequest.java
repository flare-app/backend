package de.flare.http.request;

import de.flare.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;


public class RestRequest implements Request {

	//region private members
	private HttpURLConnection connection;
	private String content;
	private String response;
	private boolean hostReachable;
	//endregion

	//region private const
	private static final Logger logger = LoggerFactory.getLogger(RestRequest.class);
	//endregion

	//region ctor
	public RestRequest(URL url) throws IOException {
		connection = (HttpURLConnection) url.openConnection();
		hostReachable = true;
	}
	//endregion

	//region request
	@Override
	public Request setMethod(String method) {
		try {
			connection.setRequestMethod(method);
		} catch (ProtocolException e) {
			logger.error("cannot set request method", e);
		}

		return this;
	}

	@Override
	public Request setProperty(String key, String value) {
		connection.setRequestProperty(key, value);
		return this;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public Request setContent(String requestContent) {

		try {
			connection.setDoOutput(true);

			DataOutputStream outputStream;

			outputStream = new DataOutputStream(connection.getOutputStream());
			outputStream.writeBytes(requestContent);
			outputStream.flush();
			outputStream.close();

			content = requestContent;
		} catch (IOException e) {
			logger.error("cannot set request content", e);
		}

		return this;
	}

	@Override
	public int getResponseCode() {

		try {
			return connection.getResponseCode();
		} catch (IOException e) {
			logger.error("cannot get request response code", e);
			return 0;
		}
	}

	@Override
	public String getResponse() {

		if (response != null) {
			return response;
		}

		BufferedReader responseReader;
		StringBuilder restResponse = new StringBuilder();

		try {

			InputStreamReader inputStreamReader;

			if (isSuccessful()) {
				inputStreamReader = new InputStreamReader(connection.getInputStream());
			} else {
				inputStreamReader = new InputStreamReader(connection.getErrorStream());
			}

			responseReader = new BufferedReader(inputStreamReader);
			String responseString;

			while ((responseString = responseReader.readLine()) != null) {
				restResponse.append(responseString);
			}
			responseReader.close();

		} catch (IOException e) {
			logger.error("cannot read request response", e);
			return RestRequest.ERROR_RESPONSE;
		}

		response = restResponse.toString();
		return response;
	}

	@Override
	public boolean isSuccessful() {
		return getResponseCode() == HttpStatus.OK;
	}

	@Override
	public boolean isHostReachable() {
		return hostReachable;
	}
	//endregion
}
