package com.example.notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.notes.model.User;
import com.example.notes.service.UserServiceImpl;

@RestController
public class UserController {

	private final UserServiceImpl userService;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserController(UserServiceImpl userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userService = userService;

		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> register(@ModelAttribute("user") User user) {
		if (user.getEmail().trim().equals("") || user.getEmail() == null) {
			return new ResponseEntity<>("{\"error\": \"Email cannot be empty\"}", HttpStatus.OK);
		} else if (user.getPassword().trim().equals("") || user.getPassword() == null) {
			return new ResponseEntity<>("{\"error\": \"Password cannot be empty\"}", HttpStatus.OK);
		} else if (user.getFullName().trim().equals("") || user.getFullName() == null) {
			return new ResponseEntity<>("{\"error\": \"fullname cannot be empty\"}", HttpStatus.OK);
		} else if (userService.findUserByEmail(user.getEmail()) != null) {
			return new ResponseEntity<>("{\"error\": \"email already exists\"}", HttpStatus.OK);
		}
		String pass = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(pass);
		user.setActive(1);
		userService.saveUser(user);
		return new ResponseEntity<>("{\"success\": \"user registration success\"}", HttpStatus.OK);
	}

}
