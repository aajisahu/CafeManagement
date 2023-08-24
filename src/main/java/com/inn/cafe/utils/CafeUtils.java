package com.inn.cafe.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CafeUtils {

//	it will contain a generic method which can be use in any service
	private CafeUtils() {

	}

	public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {

		return new ResponseEntity<String>("{\"message\":\"" + responseMessage + "\"}", httpStatus);

	}

}
