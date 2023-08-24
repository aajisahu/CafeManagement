package com.inn.cafe.serviceImpl;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inn.cafe.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public ResponseEntity<String> SignUp(Map<String, String> requestMap) {

		return null;
	}

}
