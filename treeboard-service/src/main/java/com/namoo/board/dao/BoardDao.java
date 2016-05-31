package com.namoo.board.dao;

import java.util.List;

import com.namoo.board.domain.Article;
import com.namoo.board.domain.Board;
import com.namoo.board.domain.Reply;


public interface BoardDao {
	//
	public List<Board> readAllBoard();	
	public Board readBoardBySeq(int boardSeq);	
	public List<Article> readAllArticlesByBoardSeq(int boardSeq);
	public Article readArticleByArticleSeq(int articleSeq);
	public List<Reply> readAllReplysByArticleSeq(int articleSeq);
	public Reply readReplyByReplySeq(int replySeq);
	
	public Board createBoard(Board board);	
	public Article createArticle(int boardSeq, Article article);
	public Reply createReply(int articleSeq, Reply reply);
	
	public void updateBoard(Board board);
	public void updateArticle(Article article);
	public void updateReply(Reply reply);
	
	public void removeBoard(int boardSeq);
	public void removeArticle(int articleSeq);
	public void removeReply(int replySeq);
	
}
