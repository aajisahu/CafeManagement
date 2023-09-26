package com.inn.cafe.JWT;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inn.cafe.dao.UserDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	private com.inn.cafe.modelOrPojo.User  userDetails;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		log.info("Inside load user by username: "+username);
		
		userDetails = userDao.findByEmailId(username);
		if(!Objects.isNull(userDetails)) {
			return new User(userDetails.getEmail(), userDetails.getPassword(), new ArrayList<>()); 
		}else {
			throw new UsernameNotFoundException(username);
			
		}
		
	}
	
	public com.inn.cafe.modelOrPojo.User getUserBy
}
