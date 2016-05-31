package com.namoo.board.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.namoo.board.domain.User;
import com.namoo.board.service.dto.LoginResult;
import com.namoo.board.service.facade.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String login(HttpServletRequest req) {
		//
		User login = (User) req.getSession().getAttribute("login");	// 세션에서 로그인한 유저 객체 받아오기
		
		if(login != null) {	// 로그인한 상태에서 로그인 페이지에 접속이 요청되면 메인페이지로 리다이렉트 
			//
			return "redirect:/main";
		} else {	// 로그인 하지 않은 상태면 로그인을 진행
			String loginSuccess = req.getParameter("login");	// 로그인 성공 여부
			
			if (loginSuccess != null) {	// 로그인 처리 후 돌아왔을 경우 생기는 성공 여부가 있는지 없는지 판별하여 보냄 설정 
				req.setAttribute("success", loginSuccess);
			}
			return "user/login";
		}
	}
	
	@RequestMapping("/login.do")
	public String login(HttpServletRequest req, User user) {
		//
		LoginResult result = userService.login(user.getUserId(), user.getPassword());	// 로그인을 실행해서 실행 결과를 로그인 결과 객체에 담기
		User login = result.getLoginInfo();	// 로그인 실행 결과 객체에서 로그인한 유저 로드
		if(result.isSuccess()){	// 로그인 성공 여부를 확인하고 로그인이 성공한 경우 다음으로.
			//
			String url = req.getParameter("url");
			if(url != null) {	// 로그인을 실행한 페이지 정보를 보냈는지 확인해서 로그인을 실행하고 해당 페이지로 돌려보낸다.
				req.getSession().setAttribute("login", login);	// 로그인 유저 정보를 세션에 담아서 보냄 설정 
				return "redirect:" + url;	// 이전 주소로 리다이렉트
			} else {	// 로그인을 실행한 페이지 정보가 없을 경우 메인 페이지로 리다이렉트, 때문에 메인에서 로그인을 실행할때는 URL을 보내지 않음
				req.getSession().setAttribute("login", login);
				return "redirect:/main";
			}
		} else {	// 로그인이 실패한 경우 실패 정보를 담아서 다시 로그인 페이지로 리다이렉트
			return "redirect:/user/login?success=" + result.isSuccess();
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
		//
		String url = req.getHeader("Referer");
		// 로그아웃을 실행한 페이지 정보를 받아서 로그아웃을 실행하고 해당 페이지로 돌려보낸다.
		req.getSession().removeAttribute("login");
		return "redirect:" + url;	// 이전 주소로 리다이렉트
	}
	
	@RequestMapping("/list")
	public String userList(HttpServletRequest req) {
		//
		User login = (User) req.getSession().getAttribute("login");
		
		List<User> userlist = userService.findAllUsers();
		if(login != null){
			User loginUser = userService.findUser(login.getUserId());
			req.setAttribute("login_user", loginUser);
		}
		
		req.setAttribute("userlist", userlist);

		return "user/list";
	}
	
	@RequestMapping("/info")
	public String userInfo(HttpServletRequest req) {
		//
		User login = (User) req.getSession().getAttribute("login");	// 세션에서 로그인한 유저 객체 받아오기
		
		if(login != null){	// 로그인한 유저 객체 확인 하고 로그인한 유저가 있으면 jsp에 보내기 위해 설정.
			User loginUser = userService.findUser(login.getUserId());
			req.setAttribute("login_user", loginUser);
		}
		
		String userId = req.getParameter("userId");	// 보내온 유저 ID 받아오기
		User userinfo = userService.findUser(userId);	// 정보를 출력할 유저 객체 로드
		req.setAttribute("userinfo", userinfo);	// 정보를 출력할 유저 객체 보냄 설정 
		
		return "user/info";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String userJoin(HttpServletRequest req) {
		//
		User joinUser = (User) req.getAttribute("join_user");
		
		if(joinUser != null) {	// 가입 성공시
			req.setAttribute("join_user", joinUser);
			return "user/join";
		} else {	// 가입 실패시
			return "user/join";
		}
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String userJoin(HttpServletRequest req, User user) {
		//
		userService.registUser(user);
		
		User joinUser = userService.findUser(user.getUserId());
		if(joinUser != null){	// 가입 처리 성공 여부를 확인하고 가입이 성공한 경우 다음으로.
			//
			/*String url = req.getParameter("url");
			if(url != null) {	// 가입 요청을 실행한 페이지 정보를 보냈는지 확인해서 가입 처리를 실행하고 해당 페이지로 돌려보낸다.
				req.getSession().setAttribute("join_user", joinUser);
				resp.sendRedirect(url);
			} else {
				req.getSession().setAttribute("join_user", joinUser);
				resp.sendRedirect("../main.do");
			}*/
			return "redirect:/user/join?success=success";
		} else {	// 가입 처리가 실패한 경우 다시 가입 페이지로 돌려보냄
			return "redirect:/user/join?success=fail";
		}
	}
}
