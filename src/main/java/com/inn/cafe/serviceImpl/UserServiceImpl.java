package com.inn.cafe.serviceImpl;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inn.cafe.constents.CafeConstents;
import com.inn.cafe.dao.UserDao;
import com.inn.cafe.modelOrPojo.User;
import com.inn.cafe.service.UserService;
import com.inn.cafe.utils.CafeUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public ResponseEntity<String> SignUp(Map<String, String> requestMap) {

//		log.info("inside signUp{}", requestMap);
try {
		if (validateSignUp(requestMap)) {
			
			User user = userDao.findByEmailId(requestMap.get("email"));
			
			if (Objects.isNull(user)) {

				userDao.save(getUserFromMap(requestMap));
				return CafeUtils.getResponseEntity("User Successfully Registered!", HttpStatus.OK);
				
			} else {
				return CafeUtils.getResponseEntity("Email Id already exist",HttpStatus.BAD_REQUEST);
			}

		} else {
			return CafeUtils.getResponseEntity(CafeConstents.INVALID_DATA, HttpStatus.BAD_REQUEST);
		}
	}catch(Exception ex){
		ex.printStackTrace();
	}


	return CafeUtils.getResponseEntity(CafeConstents.SOMETING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private boolean validateSignUp(Map<String, String> requestMap) {

		if (requestMap.containsKey("name") && requestMap.containsKey("email") && requestMap.containsKey("contactNumber")
				&& requestMap.containsKey("password")) {
			return true;
		} else {
			return false;
		}
	}

	private User getUserFromMap(Map<String, String> requestMap) {
		User user = new User();
		user.setName(requestMap.get("name"));
		user.setContactNumber(requestMap.get("contactNumber"));
		user.setEmail(requestMap.get("email"));
		user.setPassword(requestMap.get("password"));
		user.setStatus(requestMap.get("status"));
		user.setRole(requestMap.get("role"));

		return user;

	}

}
