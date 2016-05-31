package com.namoo.board.dao;

import java.util.List;

import com.namoo.board.domain.User;

public interface UserDao {

	 List<User> readAllUsers();
	 User readUser(String userId);
	 void createUser(User user);
	 void updateUser(User user);
	 void removeUser(String userId);
}