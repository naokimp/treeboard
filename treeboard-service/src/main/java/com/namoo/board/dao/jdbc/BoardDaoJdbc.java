package com.namoo.board.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.namoo.board.dao.BoardDao;
import com.namoo.board.domain.Article;
import com.namoo.board.domain.Board;
import com.namoo.board.domain.Reply;
import com.namoo.board.domain.User;

@Repository
public class BoardDaoJdbc implements BoardDao {

	private ReturnResources returnResources = new ReturnResources();
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Board> readAllBoard() {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> boardList = null;
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT board_seq, board_nm, board_manager_id, board_manager_nick FROM board_tb";
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			boardList = new ArrayList<Board>();			
			
			while (rset.next()) {
				int boardSeq = rset.getInt("board_seq");	// board seq
				String boardName = rset.getString("board_nm");	// board name
				String managerId = rset.getString("board_manager_id");	// manager id
				String managerNick = rset.getString("board_manager_nick");	// manager nickname
				
				System.out.println("\n" + "board seq : " + boardSeq + "\n" + "board name : " + boardName
						+ "\n" + "board manager id : " + managerId + "\n" + "board manager nickname : " + managerNick);
				User manager = new User(managerId, managerNick);
				Board board = new Board(boardSeq, boardName, manager);
				boardList.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return boardList;
	}

	@Override
	public Board readBoardBySeq(int boardSeq) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board board = null;
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT board_seq, board_nm, board_manager_id, board_manager_nick FROM board_tb WHERE board_seq = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardSeq);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				int board_seq = rset.getInt("board_seq");		// board seq
				String boardName = rset.getString("board_nm");	// board name
				String managerId = rset.getString("board_manager_id");	// manager id
				String managerNick = rset.getString("board_manager_nick");	// manager nickname
				
				System.out.println("\n" + "board seq : " + board_seq + "\n" + "board name : " + boardName
						+ "\n" + "board manager id : " + managerId + "\n" + "board manager nickname : " + managerNick);
				User manager = new User(managerId, managerNick);
				board = new Board(boardSeq, boardName, manager);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return board;
	}

	@Override
	public List<Article> readAllArticlesByBoardSeq(int boardSeq) {
		//	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Article> articleList = new ArrayList<Article>();
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT article_seq, article_title, article_date, article_content, article_author_id"
					+ ",article_author_nick FROM article_tb WHERE board_seq = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardSeq);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				int articleSeq = rset.getInt("article_seq");	// article seq
				String title = rset.getString("article_title");	// article title
				Date regDate = rset.getTimestamp("article_date"); // article date
				String content = rset.getString("article_content");	// article content
				String authorId = rset.getString("article_author_id"); // author id
				String authornick = rset.getString("article_author_nick"); // author nickname
				
				System.out.println("\n" + "article seq : " + articleSeq + "\n" + "article title : " + title 
						+ "\n" + "article date : " + regDate + "\n" + "article content : " + content
						+ "\n" + "article author id : " + authorId + "\n" + "article author nick : " + authornick);
				
				User author = new User(authorId, authornick);
				Article article = new Article(articleSeq, content, regDate, title, author);
				articleList.add(article);			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return articleList;
	}

	@Override
	public Article readArticleByArticleSeq(int articleSeq) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Article article = null;
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT article_seq, article_title, article_date, article_content, article_author_id"
					+ ",article_author_nick FROM article_tb WHERE article_seq = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, articleSeq);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				int seq = rset.getInt("article_seq");	// article seq
				String title = rset.getString("article_title");	// article title
				Date regDate = rset.getTimestamp("article_date");	// article date
				String content = rset.getString("article_content");	// article content
				String authorId = rset.getString("article_author_id"); // author id
				String authornick = rset.getString("article_author_nick"); // author nickname
				
				System.out.println("\n" + "article seq : " + seq + "\n" + "article title : " + title 
						+ "\n" + "article date : " + regDate + "\n" + "article content : " + content
						+ "\n" + "article author id : " + authorId + "\n" + "article author nick : " + authornick);
				
				User author = new User(authorId, authornick);
				article = new Article(seq, content, regDate, title, author);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return article;
	}

	@Override
	public List<Reply> readAllReplysByArticleSeq(int articleSeq) {
		//	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Reply> replies = new ArrayList<Reply>();
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT reply_seq, reply_date, reply_content, reply_author_id"
					+ ", reply_author_nick FROM reply_tb WHERE article_seq = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, articleSeq);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				int seq = rset.getInt("reply_seq");	// reply seq
				Date regDate = rset.getTimestamp("reply_date");	// reply date
				String content = rset.getString("reply_content");	// reply content
				String authorId = rset.getString("reply_author_id"); // reply id
				String authornick = rset.getString("reply_author_nick"); // reply nickname
				
				System.out.println("\n" + "reply seq : " + seq
						+ "\n" + "reply date : " + regDate + "\n" + "reply content : " + content
						+ "\n" + "reply author id : " + authorId + "\n" + "reply author nick : " + authornick);
				
				User author = new User(authorId, authornick);
				Reply reply = new Reply(seq, regDate, content, author);
				replies.add(reply);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return replies;
	}

	@Override
	public Reply readReplyByReplySeq(int replySeq) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Reply reply = null;
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT reply_seq, reply_date, reply_content, reply_author_id"
					+ ", reply_author_nick FROM reply_tb WHERE reply_seq = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, replySeq);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				int seq = rset.getInt("reply_seq");	// reply seq
				Date regDate = rset.getTimestamp("reply_date");	// reply date
				String content = rset.getString("reply_content");	// reply content
				String authorId = rset.getString("reply_author_id"); // reply id
				String authornick = rset.getString("reply_author_nick"); // reply nickname
				
				System.out.println("\n" + "reply seq : " + seq
						+ "\n" + "reply date : " + regDate + "\n" + "reply content : " + content
						+ "\n" + "reply author id : " + authorId + "\n" + "reply author nick : " + authornick);
				
				User author = new User(authorId, authornick);
				reply = new Reply(seq, regDate, content, author);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return reply;
	}
	
	
	

	@Override
	public Board createBoard(Board board) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO board_tb (board_nm, board_manager_id, board_manager_nick)"
					+ "VALUES (?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getBoardName());
			pstmt.setString(2, board.getManager().getUserId());
			pstmt.setString(3, board.getManager().getNickname());
			
			int count = pstmt.executeUpdate();
			rset = pstmt.getGeneratedKeys();
			if (rset.next()) {
				System.out.println(rset.getInt("board_seq"));
				board.setBoardSeq(rset.getInt("board_seq"));
			}
			System.out.println(count + "개의 쿼리가 실행되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return board;
	}

	@Override
	public Article createArticle(int boardSeq, Article article) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO article_tb (board_seq, article_title, article_date, article_content, article_author_id, article_author_nick)"
					+ "VALUES (?, ?, sysdate(), ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardSeq);
			pstmt.setString(2, article.getTitle());
			pstmt.setString(3, article.getContent());
			pstmt.setString(4, article.getAuthor().getUserId());
			pstmt.setString(5, article.getAuthor().getNickname());
			
			int count = pstmt.executeUpdate();
			rset = pstmt.getGeneratedKeys();
			if (rset.next()) {
				System.out.println(rset.getInt("article_seq"));
				article.setArticleSeq(rset.getInt("article_seq"));
			}
			System.out.println(count + "개의 쿼리가 실행되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return article;
	}

	@Override
	public Reply createReply(int articleSeq, Reply reply) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO reply_tb (article_seq, reply_date, reply_content, reply_author_id, reply_author_nick)"
					+ "VALUES (?, sysdate(), ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, articleSeq);
			pstmt.setString(2, reply.getContent());
			pstmt.setString(3, reply.getAuthor().getUserId());
			pstmt.setString(4, reply.getAuthor().getNickname());
			
			int count = pstmt.executeUpdate();
			rset = pstmt.getGeneratedKeys();
			if (rset.next()) {
				System.out.println(rset.getInt("reply_seq"));
				reply.setReplySeq(rset.getInt("reply_seq"));
			}
			System.out.println(count + "개의 쿼리가 실행되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return reply;
	}

	
	
	
	@Override
	public void updateBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE board_tb SET board_nm = ?, board_manager_id = ?, board_manager_nick = ?"
					+ "WHERE board_seq = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getBoardName());
			pstmt.setString(2, board.getManager().getUserId());
			pstmt.setString(3, board.getManager().getNickname());
			pstmt.setInt(4, board.getBoardSeq());
			
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			returnResources.returnResources(pstmt, conn);
		}
	}

	@Override
	public void updateArticle(Article article) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE article_tb SET article_date = sysdate(), article_title = ?"
					+ ",article_content = ? WHERE article_seq = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContent());
			pstmt.setInt(3, article.getArticleSeq());
			
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			returnResources.returnResources(pstmt, conn);
		}
	}

	@Override
	public void updateReply(Reply reply) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE reply_tb SET reply_date = sysdate()"
					+ ",reply_content = ? WHERE reply_seq = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reply.getContent());
			pstmt.setInt(2, reply.getReplySeq());
			
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			returnResources.returnResources(pstmt, conn);
		}
	}

	
	
	
	@Override
	public void removeBoard(int boardSeq) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "DELETE FROM board_tb WHERE board_seq = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardSeq);
			
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			returnResources.returnResources(pstmt, conn);
		}
	}

	@Override
	public void removeArticle(int articleSeq) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "DELETE FROM article_tb WHERE article_seq = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, articleSeq);
			
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			returnResources.returnResources(pstmt, conn);
		}
	}

	@Override
	public void removeReply(int replySeq) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "DELETE FROM reply_tb WHERE reply_seq = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, replySeq);
			
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			returnResources.returnResources(pstmt, conn);
		}
	}
}