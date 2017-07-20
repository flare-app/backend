package de.flare.core;


import de.flare.properties.PropertyEditor;
import de.flare.properties.SimplePropertyEditor;

/**
 * This class contains the main entry point for the flare backend.
 */
public final class Application {

	//region private const
	private static final String SPLIT_PROPERTIES = "=";
	//endregion

	//region ctor

	/**
	 * This constructor prevents anyone from creating an instance of this class.
	 */
	private Application() {

	}
	//endregion

	//region public static methods
	/**
	 * This method is the main entrance point for the application.
	 * @param args the start parameter for the application
	 */
    public static void main(String[] args) {
		setProperties(args);
    }
    //endregion

	//region private static methods
	/**
	 * This method sets all properties from the given start parameters.
	 * @param args the application start parameters
	 */
	private static void setProperties(String[] args) {
		for (String arg : args) {
			String[] splitArg = arg.split(SPLIT_PROPERTIES);

			if (splitArg.length != 2) {
				continue;
			}

			PropertyEditor.setString(splitArg[0], splitArg[1]);
		}
	}
	//endregion
}
