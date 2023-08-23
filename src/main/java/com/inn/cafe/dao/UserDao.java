package com.inn.cafe.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inn.cafe.modelOrPojo.User;

public interface UserDao extends JpaRepository<User, Integer> {

}
