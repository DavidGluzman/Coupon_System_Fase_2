package com.davidgluzman.couponsys.utils;

import java.sql.Date;

public class DateUtils {
	public static Date convertDate(java.util.Date date) {
		return new java.sql.Date(date.getYear() - 1900, date.getMonth(), date.getDate());
	}
}