package com.dgsh.rest.webservices.restfulwebservices.user;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/users")
	public List<User> retrievAllUsers(){
		return userDao.findAll(); 
	}
	
	//EntityModel
	//WebMvcLinkBuilder
	
	@GetMapping("/users/{id}")
	public EntityModel<User> findOne(@PathVariable("id") int id) {
		User user = userDao.findOne(id);
		if(user==null) {
			throw new UserNotFoundException("id: " + id);
		}
		EntityModel<User> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrievAllUsers());
		entityModel.add(link.withRel("all/users"));
		return entityModel;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteOne(@PathVariable("id") int id) {
		userDao.deleteOne(id);
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> creatUser(@Valid @RequestBody User user) {
		 userDao.createUser(user);
		 
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				 .path("/{id}")
				 .buildAndExpand(user.getId())
				 .toUri();
		 return ResponseEntity.created(location ).build();
	}
	
}
