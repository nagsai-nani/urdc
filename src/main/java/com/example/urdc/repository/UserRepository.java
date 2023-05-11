package com.example.urdc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.urdc.models.User;

public interface UserRepository extends MongoRepository<User,String>{

}
