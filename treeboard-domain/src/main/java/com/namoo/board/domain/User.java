package com.namoo.board.domain;

/**
 * @author Administrator
 * @version 1.0
 * @created 02-4-2014 ���� 5:16:52
 */
public class User {

	private String userId;
	private String password;
	private String name;
	private String email;
	private String nickname;
	
	public User() {
	}

	public User(String userId, String password, String name, String email, String nickname) {
		//
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.nickname = nickname;
	}
	
	public User(String userId, String password, String nickname) {
		//
		this.userId = userId;
		this.nickname = nickname;
	}
	
	public User(String userId, String nickname) {
		//
		this.userId = userId;
		this.nickname = nickname;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}