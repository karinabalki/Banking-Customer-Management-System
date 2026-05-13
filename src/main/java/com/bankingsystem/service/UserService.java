package com.bankingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.bankingsystem.model.User;
import com.bankingsystem.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getLogin(String username, String password) {
		
		return userRepository.findByUsernameAndPassword(username, password);
	}
	
	
	public User getRegister(User user) {
		
		user.setRole("USER");
		return userRepository.save(user);
	}
	
    public List<User> getAllUsers() {   
	    return userRepository.findAll();
	}

	public User getUserById(Long id) {
	    return userRepository.findById(id).orElseThrow();
	}

}
