package com.example.urdc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.urdc.dao.UserDao;
import com.example.urdc.dto.UserDto;
import com.example.urdc.models.User;
import com.example.urdc.response.dto.UserResponse;
@Service
public class UserService {
@Autowired 
UserDao dao;
	public User save(User user) throws Exception {
		User userBean= dao.getByUserName(user.getUserName());
		if((user.getAadharNumber()==null||user.getAadharNumber().isEmpty()
		 		||(user.getUserName()==null||user.getUserName().isEmpty()))){
			throw new Exception("The required fields are missing");
		}
		if(userBean!=null) {
			throw new Exception("User already exist "+user.getUserName()+" Duplicates are not allowed");
		}
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
	public List<UserResponse> getByAadharNumber(String aadharNumber) {
		List<User> users=dao.getByAadharNumber(aadharNumber);
		List<UserResponse> resp=new ArrayList<UserResponse>();
		UserResponse response=null;
		for (User user : users) {
		response=new UserResponse();
		response.setAadharNumber(user.getAadharNumber());
		response.setAddress(user.getAddress());
		response.setContactNumber(user.getContactNumber());
		response.setEmpId(user.getEmpId());
		response.setSalary(user.getSalary());
		response.setUserName(user.getUserName());
		response.setDetails(user.getDetails());
		resp.add(response);
		}
		return resp;
	}
	
	public static  UserResponse getResponse(String aadharNumber) {
		UserResponse resp=new UserResponse(aadharNumber);
		System.out.println(resp);
		return resp;	
	}
	
	public static void main(String[] args) {
		getResponse("098765434567");
	}
	
	}


