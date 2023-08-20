package com.example.urdc.dao;


import java.util.List;

import com.example.urdc.dto.UserDto;
import com.example.urdc.models.User;

public interface UserDao {

	User save(User user);

	User getByUserName(String userName);
	
	long updateUser(UserDto dto);

	List<User> getByAadharNumber(String aadharNumber);

}
