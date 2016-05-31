package com.namoo.board.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namoo.board.dao.UserDao;
import com.namoo.board.domain.User;
import com.namoo.board.service.dto.LoginResult;
import com.namoo.board.service.facade.UserService;

@Service
public class UserServiceLogic implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public LoginResult login(String userId, String password) {
		
		LoginResult result = new LoginResult(new User(userId, password, ""), false);
		User user = userDao.readUser(userId);
		
		if (user != null) {
			if (user.getPassword().equals(password)) {
				result.setSuccess(true);
			} else {
				result.setMessage("패스워드가 올바르지 않습니다.");
			}
		} else {
			result.setMessage("존재하지 않는 사용자입니다.");
		}
		result.getLoginInfo().setNickname(user.getNickname());
		return result;
	}

	@Override
	public List<User> findAllUsers() {
		//
		List<User> userList = userDao.readAllUsers();
		return userList;
	}

	@Override
	public User findUser(String userId) {
		//
		User user = userDao.readUser(userId);
		return user;
	}

	@Override
	public void registUser(User user) {
		//
		userDao.createUser(user);
	}

	@Override
	public void modifyUser(User user) {
		//
		userDao.updateUser(user);
	}

	@Override
	public void removeUser(String userId) {
		//
		userDao.removeUser(userId);
	}
	
}
