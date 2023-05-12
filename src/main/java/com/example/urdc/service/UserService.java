package com.example.urdc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.urdc.dao.UserDao;
import com.example.urdc.dto.UserDto;
import com.example.urdc.models.User;
@Service
public class UserService {
@Autowired 
UserDao dao;
	public User save(User user) {
		return dao.save(user);
	}
	public User getByUserName(String userName) {
		return dao.getByUserName(userName);
	}
	public void updateUser(UserDto dto) throws Exception {
		
		long count=dao.updateUser(dto);
		if(count<=0) {
			throw new Exception("No USER FOUND"+dto.getUserName());
		}
	}
	
	}


