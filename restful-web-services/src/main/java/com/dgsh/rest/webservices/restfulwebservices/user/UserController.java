package com.dgsh.rest.webservices.restfulwebservices.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/users")
	public List<User> retrievAllUsers(){
		return userDao.findAll(); 
	}
	
	@GetMapping("/users/{id}")
	public User findOne(@PathVariable("id") int id) {
		return userDao.findOne(id);
	}
	
	@PostMapping("/users")
	public User creatUser(@RequestBody User user) {
		return userDao.createUser(user);
	}
	
}
