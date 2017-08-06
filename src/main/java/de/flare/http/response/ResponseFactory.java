package de.flare.http.response;

import de.flare.http.RequestContext;

import java.util.HashMap;
import java.util.Map;

public final class ResponseFactory {

	//region private static members
	private static Map<Language, ResponseTexts> responses = new HashMap<>();
	//endregion

	//region private const
	private static final Language DEFAULT_LANGUAGE = Language.DE_DE;

	private static final String LANGUAGE_PARAMETER = "language";
	//endregion

	//region ctor
	static {
		responses.put(Language.DE_DE, new ResponseTexts_DE_DE());
		responses.put(Language.EN_US, new ResponseTexts_EN_US());
	}

	private ResponseFactory() { }
	//endregion

	//region public static methods
	public static RequestContext setResponseTexts(RequestContext context) {
		Language requestLanguage;

		try {
			requestLanguage = Language.valueOf(context.getRequest().queryParamOrDefault(LANGUAGE_PARAMETER, DEFAULT_LANGUAGE.toString()));
		} catch (Exception e) {
			// suppress exception
			requestLanguage = DEFAULT_LANGUAGE;
		}

		return context.setResponseMessages(responses.get(requestLanguage));
	}
	//endregion
}
