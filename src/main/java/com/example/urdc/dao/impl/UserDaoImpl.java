package com.example.urdc.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.urdc.dao.UserDao;
import com.example.urdc.models.User;
import com.example.urdc.repository.UserRepository;
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

}
