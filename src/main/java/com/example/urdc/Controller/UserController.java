package com.example.urdc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.urdc.models.User;
import com.example.urdc.service.UserService;

@RestController
@RequestMapping("/rest/user")
public class UserController {
@Autowired
UserService service;

@PostMapping("/save")

public ResponseEntity<User> saveUser(@RequestBody User user){
	return ResponseEntity.ok(service.save(user));
	
}

}
