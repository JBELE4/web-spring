package com.info.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.info.Respository.UserRepository;
import com.info.model.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}
	
	// use to GET one user by id
	public Optional<User> getById (long userId) {
	Optional<User> user = userRepository.findById(userId);
		return user;
	}

	public User save(User user) {
		user.setActive(1);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return user;
	}

	public void update(User user) {
		userRepository.saveAndFlush(user);
	}
	
	// use to PUT (update) one item by id
	public User updateByField(long userId, User newUser) {
		
		Optional<User> aux = userRepository.findById(userId);
		User aux2 = aux.get();

		if (aux2 != null) {
			aux2.setFirstName(newUser.getFirstName());
			aux2.setLastName(newUser.getLastName());
			aux2.setEmail(newUser.getEmail());
			aux2.setPassword(passwordEncoder.encode(newUser.getPassword()));
			aux2.setRole(newUser.getRole());
			aux2.setActive(1);
		}

		User defUser = new User(userId,aux2);
		userRepository.saveAndFlush(defUser);
		return defUser;

	}

	public List<User> findAllUser() {
		return userRepository.findAll();
	}

	public void deleteUser(long userId) {
		userRepository.deleteById(userId);
	}

}
