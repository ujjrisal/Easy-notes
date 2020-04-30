package com.example.notes.service;

import com.example.notes.model.User;

public interface UserService {

	public User findUserByEmail(String email);

	public void saveUser(User user);

}
