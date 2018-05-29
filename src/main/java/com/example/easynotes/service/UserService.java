package com.example.easynotes.service;

import com.example.easynotes.model.User;

public interface UserService {

	public User findUserByEmail(String email);

	public void saveUser(User user);

}
