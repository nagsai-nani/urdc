package com.example.urdc.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.urdc.dao.UserDao;
import com.example.urdc.dto.UserDto;
import com.example.urdc.models.User;
import com.example.urdc.repository.UserRepository;
import com.mongodb.client.result.UpdateResult;
@Repository
public class UserDaoImpl implements UserDao{
@Autowired 
MongoTemplate temp;

@Autowired
UserRepository repo;

	@Override
	public User save(User user) {
		return repo.save(user);
	}

	@Override
	public User getByUserName(String userName) {
		Query query =new Query();
		query.addCriteria(Criteria.where("userName").is(userName));
		return temp.findOne(query, User.class);
	}


	@Override
	public long updateUser(UserDto dto) {
		Query query=new Query();
		query.addCriteria(Criteria.where("userName").is(dto.getUserName()));
		
		Update update=new Update();
		update.set("aadharNumber", dto.getAadharNumber());
		update.set("userName", dto.getUserName());
		update.set("password", dto.getPassword());
		update.set("address", dto.getAddress());
		update.set("contactNumber", dto.getContactNumber());
		update.set("salary", dto.getSalary());
		update.set("empId", dto.getEmpId());
		UpdateResult updateResult=temp.updateFirst(query, update, User.class);

		
		return updateResult.getModifiedCount();
		
	}

	@Override
	public List<User> getByAadharNumber(String aadharNumber) {
		Query query=new Query();
		query.addCriteria(Criteria.where("aadharNumber").is(aadharNumber));
		return temp.find(query, User.class);
	}

}
