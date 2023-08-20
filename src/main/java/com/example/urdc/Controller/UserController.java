package com.example.urdc.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.urdc.dto.UserDto;
import com.example.urdc.models.User;
import com.example.urdc.response.dto.UserResponse;
import com.example.urdc.service.UserService;

@RestController
@RequestMapping("/rest/user")
public class UserController {
@Autowired
UserService service;

@PostMapping("/save")

public ResponseEntity<User> saveUser(@RequestBody User user) throws Exception{
	return ResponseEntity.ok(service.save(user));
	
}
@GetMapping("/get/{userName}")
public ResponseEntity<User> getByUserName(@PathVariable String userName){
	return ResponseEntity.ok(service.getByUserName(userName));
	
}
@PutMapping("/update")
public ResponseEntity<String> updateUser(@RequestBody UserDto dto) throws Exception{
	service.updateUser(dto);
	return ResponseEntity.ok("updated");
	
}

@GetMapping("/get/aadharNumber/{aadharNumber}")
public ResponseEntity<List<UserResponse>> getUserName(@PathVariable String aadharNumber){
	return ResponseEntity.ok(service.getByAadharNumber(aadharNumber));
	
}
}
