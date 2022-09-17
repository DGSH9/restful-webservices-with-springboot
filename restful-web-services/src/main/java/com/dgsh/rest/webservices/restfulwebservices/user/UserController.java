package com.dgsh.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		User user = userDao.findOne(id);
		if(user==null) {
			throw new UserNotFoundException("id: " + id);
		}
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> creatUser(@RequestBody User user) {
		 userDao.createUser(user);
		 
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				 .path("/{id}")
				 .buildAndExpand(user.getId())
				 .toUri();
		 return ResponseEntity.created(location ).build();
	}
	
}
