package com.mun.workshopmongodb.repository.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Instant;
import java.time.format.DateTimeParseException;

public class URL {

	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static Instant convertInstant(String strInstant, Instant defaultInstant) {
		try {
			return Instant.parse(strInstant);
		} catch (DateTimeParseException e) {
			return defaultInstant;
		}
	}
	
}
