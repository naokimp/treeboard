package com.namoo.board.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.namoo.board.domain.Article;
import com.namoo.board.domain.Board;
import com.namoo.board.domain.Reply;
import com.namoo.board.domain.User;
import com.namoo.board.service.facade.BoardService;
import com.namoo.board.service.facade.UserService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private UserService userService;

	@RequestMapping("/main")
	public String main(HttpServletRequest req) {
		//
		User login = (User) req.getSession().getAttribute("login");
		
		if(login != null){
			User loginUser = userService.findUser(login.getUserId());
			req.setAttribute("login_user", loginUser);
		}
		return "main";
	}

	@RequestMapping("/board/list")
	public String boardList(HttpServletRequest req) {
		//
		List<Board> boardList = boardService.findAllBoards();	// 전체 게시판 리스트 로드 
		User login = (User) req.getSession().getAttribute("login");	// 세션에서 로그인한 유저 객체 받아오기 

		if(login != null){	// 로그인한 유저 객체 확인 하고 로그인한 유저가 있으면 jsp에 보내기 위해 설정.
			User loginUser = userService.findUser(login.getUserId());
			req.setAttribute("login_user", loginUser);
		}
		
		req.setAttribute("board_list", boardList);	// 게시판 리스트 보냄 설정
		
		return "board/board_list";
	}
	
	@RequestMapping(value = "/board/create", method = RequestMethod.GET)
	public String boardCreate(HttpServletRequest req) {
		//
		User login = (User) req.getSession().getAttribute("login");	// 세션에서 로그인한 유저 객체 받아오기
		
		if(login != null){	// 로그인한 유저 객체 확인 하고 로그인한 유저가 있으면 jsp에 보내기 위해 설정.
			User loginUser = userService.findUser(login.getUserId());
			req.setAttribute("login_user", loginUser);
		}
		
		return "board/board_create";
	}
	
	@RequestMapping(value = "/board/create", method = RequestMethod.POST)
	public String boardCreate(HttpServletRequest req, 
			@RequestParam("board_name") String boardName) {
		//
		User login = (User) req.getSession().getAttribute("login");	// 세션에서 로그인한 유저 객체 받아오기
		
		if(login != null){	// 로그인한 유저 객체 확인 하고 로그인한 유저가 있으면 jsp에 보내기 위해 설정.
			User loginUser = userService.findUser(login.getUserId());
			req.setAttribute("login_user", loginUser);
		}
		User manager = login;	// manager를 로그인한 유저로 선언
		
		int boardSeq = boardService.createBoard(new Board(boardName, manager)).getBoardSeq();	// 게시판 생성 (반환되는 게시판 번호)
		
		return "redirect:/board/article/list?board_seq=" + boardSeq;
	}
	
	@RequestMapping("/board/article/list")
	public String articleList(HttpServletRequest req) {
		//
		String boardSeq = req.getParameter("board_seq");	// 보내온 게시판 번호 받아오기
		Board board = boardService.findBoardByBoardSeq(Integer.parseInt(boardSeq));	// 받은 게시판 번호로 Board 객체 로드 
		List<Article> articleList = boardService.findAllArticlesByBoardSeq(board.getBoardSeq());	// 받은 게시판 번호로 게시글 목록 로드

		User login = (User) req.getSession().getAttribute("login");	// 세션에서 로그인한 유저 객체 받아오기 

		if(login != null){	// 로그인한 유저 객체 확인 하고 로그인한 유저가 있으면 jsp에 보내기 위해 설정.
			User loginUser = userService.findUser(login.getUserId());
			req.setAttribute("login_user", loginUser);
		}
		
		req.setAttribute("article_list", articleList);	// 게시물 목록 보냄 설정
		req.setAttribute("board", board);	// 게시판 객체 보냄 설정
		
		return "board/article_list";
	}
	
	@RequestMapping("/board/article/content")
	public String articleContent(HttpServletRequest req) {
		//
		User login = (User) req.getSession().getAttribute("login");	// 세션에서 로그인한 유저 객체 받아오기
		
		if(login != null){	// 로그인한 유저 객체 확인 하고 로그인한 유저가 있으면 jsp에 보내기 위해 설정.
			User loginUser = userService.findUser(login.getUserId());
			req.setAttribute("login_user", loginUser);
		}
		String boardSeq = req.getParameter("board_seq");	// 보내온 게시판 번호 받아오기
		
		int articleSeq = 0;
		if (req.getParameter("article_seq") != null ) {
			articleSeq = Integer.parseInt(req.getParameter("article_seq"));	// 보내온 게시글 번호 받아오기
		} else {
			articleSeq = (int) req.getAttribute("article_seq");	// 보내온 게시글 번호 받아오기
		}

		Article article = boardService.findArticleByArticleSeq(articleSeq);
		List<Reply> replies = article.getReplies();
		
		req.setAttribute("board_seq", boardSeq);
		req.setAttribute("article", article);
		req.setAttribute("replies", replies);
		
		return "board/article_content";
	}
	
	@RequestMapping(value = "/board/article/write", method = RequestMethod.GET)
	public String articleWrite(HttpServletRequest req) {
		//
		User login = (User) req.getSession().getAttribute("login");	// 세션에서 로그인한 유저 객체 받아오기
		
		if(login != null){	// 로그인한 유저 객체 확인 하고 로그인한 유저가 있으면 jsp에 보내기 위해 설정.
			User loginUser = userService.findUser(login.getUserId());
			req.setAttribute("login_user", loginUser);
		}
		String boardSeq = req.getParameter("board_seq");	// 보내온 게시판 번호 받아오기
		
		req.setAttribute("board_seq", boardSeq);	// 게시판 번호 보냄 설정
		
		return "board/article_write";
	}
	
	@RequestMapping(value = "/board/article/write", method = RequestMethod.POST)
	public String articleWrite(HttpServletRequest req, Article article) {
		//
		User login = (User) req.getSession().getAttribute("login");	// 세션에서 로그인한 유저 객체 받아오기
		
		if(login != null){	// 로그인한 유저 객체 확인 하고 로그인한 유저가 있으면 jsp에 보내기 위해 설정.
			User loginUser = userService.findUser(login.getUserId());
			req.setAttribute("login_user", loginUser);
		}
		String boardSeq = req.getParameter("board_seq");	// 보내온 게시판 번호 받아오기
		
		article.setAuthor(userService.findUser(login.getUserId()));
		
		int articleSeq = boardService.writeArticle(Integer.parseInt(boardSeq), article).getArticleSeq();
		
		req.setAttribute("board_seq", boardSeq);
		req.setAttribute("article_seq", articleSeq);

		return "redirect:/board/article/content";
	}
	
	@RequestMapping(value = "/board/article/modify", method = RequestMethod.GET)
	public String articleModify(HttpServletRequest req) {
		//
		User login = (User) req.getSession().getAttribute("login");	// 세션에서 로그인한 유저 객체 받아오기
		
		if(login != null){	// 로그인한 유저 객체 확인 하고 로그인한 유저가 있으면 jsp에 보내기 위해 설정.
			User loginUser = userService.findUser(login.getUserId());
			req.setAttribute("login_user", loginUser);
		}
		String articleSeq = req.getParameter("article_seq");	// 보내온 게시글 번호 받아오기
		Article article = boardService.findArticleByArticleSeq(Integer.parseInt(articleSeq));
		
		String boardSeq = req.getParameter("board_seq");	// 보내온 게시판 번호 받아오기
		
		req.setAttribute("board_seq", boardSeq);	// 게시판 번호 보냄 설정
		req.setAttribute("article", article);

		return "board/article_modify";
	}
	
	@RequestMapping(value = "/board/article/modify", method = RequestMethod.POST)
	public String articleModify(HttpServletRequest req, Article article) {
		//
		User login = (User) req.getSession().getAttribute("login");	// 세션에서 로그인한 유저 객체 받아오기
		
		if(login != null){	// 로그인한 유저 객체 확인 하고 로그인한 유저가 있으면 jsp에 보내기 위해 설정.
			User loginUser = userService.findUser(login.getUserId());
			req.setAttribute("login_user", loginUser);
		}
		String boardSeq = req.getParameter("board_seq");	// 보내온 게시판 번호 받아오기
		
		int articleSeq = Integer.parseInt(req.getParameter("article_seq"));
		article.setAuthor(userService.findUser(login.getUserId()));
		article.setArticleSeq(articleSeq);
		boardService.modifyArticle(article);
		
		req.setAttribute("board_seq", boardSeq);
		req.setAttribute("article_seq", articleSeq);

		return "redirect:/board/article/content";
	}
	
	@RequestMapping("/board/article/delete")
	public String articleDelete(HttpServletRequest req) {
		//
		String boardSeq = req.getParameter("board_seq");	// 보내온 게시판 번호 받아오기
		int articleSeq = Integer.parseInt(req.getParameter("article_seq"));
		
		boardService.deleteArticle(articleSeq);

		return "redirect:/board/article/list?board_seq=" + boardSeq;
	}
	
	@RequestMapping("/board/reply/content")
	public String replyContent(HttpServletRequest req) {
		//
		User login = (User) req.getSession().getAttribute("login");	// 세션에서 로그인한 유저 객체 받아오기
		
		if(login != null){	// 로그인한 유저 객체 확인 하고 로그인한 유저가 있으면 jsp에 보내기 위해 설정.
			User loginUser = userService.findUser(login.getUserId());
			req.setAttribute("login_user", loginUser);
		}
//		String articleSeq = req.getParameter("article_seq");	// 보내온 게시글 번호 받아오기
		
		return "board/article_content";
	}
	
	@RequestMapping("/board/reply/write")
	public String replyWrite(HttpServletRequest req, Reply reply) {
		//
		User login = (User) req.getSession().getAttribute("login");	// 세션에서 로그인한 유저 객체 받아오기
		
		if(login != null){	// 로그인한 유저 객체 확인 하고 로그인한 유저가 있으면 jsp에 보내기 위해 설정.
			User loginUser = userService.findUser(login.getUserId());
			req.setAttribute("login_user", loginUser);
		}
		String boardSeq = req.getParameter("board_seq");	// 보내온 게시판 번호 받아오기
		
		int	articleSeq = Integer.parseInt(req.getParameter("article_seq"));	// 보내온 게시글 번호 받아오기
		
		reply.setAuthor(userService.findUser(login.getUserId()));
		
		boardService.writeReply(articleSeq, reply);
		
		req.setAttribute("board_seq", boardSeq);
		req.setAttribute("article_seq", articleSeq);

		return "redirect:/board/article/content";
	}
	
	@RequestMapping("/board/reply/modify")
	public String replyModify(HttpServletRequest req) {
		//
		String boardSeq = req.getParameter("board_seq");	// 보내온 게시판 번호 받아오기
		String articleSeq = req.getParameter("article_seq");
		int replySeq = Integer.parseInt(req.getParameter("reply_seq"));
		String content = req.getParameter("content");
		User author = userService.findUser(req.getParameter("author_id"));

		Reply reply = new Reply(replySeq, content, author);
		
		boardService.modifyReply(reply);

		return "redirect:/board/article/content?board_seq=" + boardSeq + "&article_seq=" + articleSeq;
	}
	
	@RequestMapping("/board/reply/delete")
	public String replyDelete(HttpServletRequest req) {
		//
		String boardSeq = req.getParameter("board_seq");	// 보내온 게시판 번호 받아오기
		String articleSeq = req.getParameter("article_seq");
		int replySeq = Integer.parseInt(req.getParameter("reply_seq"));
		
		boardService.deleteReply(replySeq);

		return "redirect:/board/article/content?board_seq=" + boardSeq + "&article_seq=" + articleSeq;
	}
}
