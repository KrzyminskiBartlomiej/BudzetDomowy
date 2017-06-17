package com.homebudget.Utils;

/**
 * Class that allow to handle text properties. In the future there will be
 * possibility to change style or font, language etc.
 * 
 * @author Bartlomiej Krzyminski
 * @since v1.00
 *
 */

public class TextEdit {
	public static String fontType;
	public static Double titleFontSize;
	public static Double plainFontSize;
	public static Double credFontSize;

	/**
	 * Used to set font parameters
	 * 
	 * @param ft
	 *            font type used in entire application
	 * @param plainFs
	 *            font size for plain text
	 * @param credFs
	 *            font size for credentials in logInView
	 * @param titleFs
	 *            font size for titles
	 *            
	 */

	public void setTextValue(String ft, Double plainFs, Double credFs, Double titleFs) {
		fontType = ft;
		titleFontSize = titleFs;
		plainFontSize = plainFs;
		credFontSize = credFs;
	}
}
