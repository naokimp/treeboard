package com.namoo.board.service.facade;

import java.util.List;

import com.namoo.board.domain.Article;
import com.namoo.board.domain.Board;
import com.namoo.board.domain.Reply;

public interface BoardService {
	//
	List<Board> findAllBoards();
	
	Board findBoardByBoardSeq(int boardSeq);
	
	
	List<Article> findAllArticlesByBoardSeq(int boardSeq);
	
	Article findArticleByArticleSeq(int articleSeq);
	
	
	List<Reply> findAllRepliesByArticleNo(int articleSeq);
	
	Reply findReplyByReplySeq(int replySeq);
	
	
	Board createBoard(Board board);
	
	Article writeArticle(int boardSeq, Article article);
	
	Reply writeReply(int articleSeq, Reply reply);
	
	
	void modifyBoard(Board board);
	
	void modifyArticle(Article article);
	
	void modifyReply(Reply reply);
	
	
	void removeBoard(int boardSeq);
	
	void deleteArticle(int articleSeq);
	
	void deleteReply(int replySeq);
}
