package com.namoo.board.service.facade;

import java.util.List;

import com.namoo.board.domain.User;
import com.namoo.board.service.dto.LoginResult;

public interface UserService {
	
	LoginResult login(String userId, String password);
	
	List<User> findAllUsers();
	
	User findUser(String userId);
	
	void registUser(User user);
	
	void modifyUser(User user);
	
	void removeUser(String userId);
}
