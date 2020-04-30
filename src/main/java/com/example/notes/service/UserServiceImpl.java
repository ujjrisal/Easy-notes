package com.example.notes.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.notes.model.Role;
import com.example.notes.model.User;
import com.example.notes.repository.RoleRepository;
import com.example.notes.repository.UserRepository;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		Role userRole = roleRepository.findByRole("ADMIN");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

}
