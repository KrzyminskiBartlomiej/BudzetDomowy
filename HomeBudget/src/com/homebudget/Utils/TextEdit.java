package com.homebudget.Utils;

public class TextEdit {
	public static String fontType;
	public static Double titleFontSize;
	public static Double plainFontSize;
	public static Double credFontSize;
	
	public void setTextValue(String ft, Double plainFs, Double credFs, Double titleFs){
		fontType = ft;
		titleFontSize = titleFs;
		plainFontSize = plainFs;
		credFontSize = credFs;
	}
}
