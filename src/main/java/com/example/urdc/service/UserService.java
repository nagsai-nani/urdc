package com.example.urdc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.urdc.dao.UserDao;
import com.example.urdc.models.User;
@Service
public class UserService {
@Autowired 
UserDao dao;
	public User save(User user) {
		return dao.save(user);
	}

}
