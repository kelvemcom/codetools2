package com.kelvem.codetool2.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateUtils {

	public static Date getCurrentDate() {
		return new Date();
	}

	public static String getDateString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

	public static String getDateString(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	
}
