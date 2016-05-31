package com.namoo.board.service.dto;

import com.namoo.board.domain.User;

public class LoginResult {
	//
	/** 로그인 요청정보 */
	private User loginInfo;
	
	/** 로그인 성공여부 */
	private boolean success; 
	
	/** 로그인 결과 메시지 (실패사유) */
	private String message;

	//--------------------------------------------------------------------------
	// 
	
	public LoginResult(User loginInfo, boolean success) {
		//
		this.loginInfo = loginInfo;
		this.success = success;
		this.message = "";
	}

	public LoginResult(User loginInfo, boolean success, String message) {
		//
		this.loginInfo = loginInfo;
		this.success = success;
		this.message = message;
	}

	public User getLoginInfo() {
		return loginInfo;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
