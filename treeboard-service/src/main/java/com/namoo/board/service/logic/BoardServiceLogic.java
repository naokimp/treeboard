package com.namoo.board.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namoo.board.dao.BoardDao;
import com.namoo.board.domain.Article;
import com.namoo.board.domain.Board;
import com.namoo.board.domain.Reply;
import com.namoo.board.service.facade.BoardService;

@Service
public class BoardServiceLogic implements BoardService{
	
	@Autowired
	private BoardDao dao;
	
	@Override
	public List<Board> findAllBoards() {
		//
		List<Board> boardList = dao.readAllBoard();
		return boardList;
	}

	@Override
	public Board findBoardByBoardSeq(int boardSeq) {
		//
		Board board = dao.readBoardBySeq(boardSeq);
		return board;
	}

	@Override
	public List<Article> findAllArticlesByBoardSeq(int boardSeq) {
		//
		List<Article> articleList = dao.readAllArticlesByBoardSeq(boardSeq);
		return articleList;
	}

	@Override
	public Article findArticleByArticleSeq(int articleSeq) {
		//
		Article article = dao.readArticleByArticleSeq(articleSeq);
		List<Reply> replies = dao.readAllReplysByArticleSeq(articleSeq);
		article.setReplies(replies);
		return article;
	}

	@Override
	public List<Reply> findAllRepliesByArticleNo(int articleSeq) {
		//
		List<Reply> replyList = dao.readAllReplysByArticleSeq(articleSeq);
		return replyList;
	}

	@Override
	public Reply findReplyByReplySeq(int replySeq) {
		//
		Reply reply = dao.readReplyByReplySeq(replySeq);
		return reply;
	}

	
	
	
	@Override
	public Board createBoard(Board board) {
		//
		return dao.createBoard(board);
	}

	@Override
	public Article writeArticle(int boardSeq, Article article) {
		//
		return dao.createArticle(boardSeq, article);
	}

	@Override
	public Reply writeReply(int articleSeq, Reply reply) {
		//
		return dao.createReply(articleSeq, reply);
	}
	
	
	

	@Override
	public void modifyBoard(Board board) {
		//
		dao.updateBoard(board);
	}

	@Override
	public void modifyArticle(Article article) {
		//
		dao.updateArticle(article);
	}

	@Override
	public void modifyReply(Reply reply) {
		//
		dao.updateReply(reply);
	}
	
	
	

	@Override
	public void removeBoard(int boardSeq) {
		//
		dao.removeBoard(boardSeq);
	}

	@Override
	public void deleteArticle(int articleSeq) {
		//
		List<Reply> replies = dao.readAllReplysByArticleSeq(articleSeq);
		for (Reply reply : replies) {
			dao.removeReply(reply.getReplySeq());
		}
		dao.removeArticle(articleSeq);
	}

	@Override
	public void deleteReply(int replySeq) {
		//
		dao.removeReply(replySeq);
	}
	
}
