package com.example.springboot.controller;

import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	public UserService service;
	
	@GetMapping
	public List<User> getAll() {
    return service.findAll();
	}
	
	@PostMapping
	public User create(@RequestBody User entity) {
    return service.save(entity);
	}
	
	@DeleteMapping
	public void deleteAll() {
    service.deleteAll();
	}
}
