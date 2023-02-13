package com.info.RESTController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.info.model.User;
import com.info.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserRESTController {

	@Autowired
	UserService service;

	// Get all users
	@GetMapping("/users")
	public ResponseEntity<Collection<User>> users() {
		Collection<User> users = service.findAllUser();
		List<User> userAux = (List<User>) users;
		for(User u : userAux) {
			u.setPassword("**");
			u.setRole("**");
		}
		Collection<User> users2 = userAux;

		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<>(users2, HttpStatus.OK);

	}

	// Get one user by email
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<User> user(@RequestParam(value = "email") String email) {
		User us = service.findByEmail(email);
		if (us != null) {
			us.setRole("**");
			us.setPassword("**");
			return new ResponseEntity<>(us, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Get one user
	@GetMapping("/{id}")
	public ResponseEntity<User> user(@PathVariable int id) {
		Optional<User> op = service.getById(id);
		if (op.isPresent()) {
			User user = op.get();
			user.setRole("**");
			user.setPassword("**");
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// use to POST an item
	@PostMapping("/insertUser")
	@ResponseStatus(HttpStatus.CREATED) // Send the status of the post
	public ResponseEntity<User> insertUser(Model model, @RequestBody User user) {

		if (service.save(user) != null) {
			user.setRole("**");
			user.setPassword("**");
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
	}

	// Remove one item by id
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<User> deleteItem(Model model, @PathVariable int id) {

		Optional<User> op = service.getById(id);

		if (op.isPresent()) {
			User user = op.get();
			service.deleteUser(id);
			user.setRole("**");
			user.setPassword("**");
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Update user by id
	@PutMapping("/{id}/update")
	public ResponseEntity<User> updateUserID(@PathVariable int id, @RequestBody User updateUser) {

		User U = service.updateByField(id, updateUser);

		if (U != null) {
			U.setRole("**");
			U.setPassword("**");
			return new ResponseEntity<>(U, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
