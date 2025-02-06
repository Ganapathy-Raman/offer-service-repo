package com.rts.tap.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

	public static Date getCurrentDate() {
		return new Date();
	}

	public static String getCurrentDateFormatted() {
		return dateFormat.format(getCurrentDate());
	}

}
