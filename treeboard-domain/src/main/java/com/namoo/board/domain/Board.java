package com.namoo.board.domain;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @created 02-4-2014 ���� 5:16:52
 */
public class Board {

	private int boardSeq;
	private String boardName;
	private User manager;
	private List<Article> articles;
	
	public Board(int boardSeq, String boardName, User manager, List<Article> articles) {
		//
		this.boardSeq = boardSeq;
		this.boardName = boardName;
		this.manager = manager;
		this.articles = articles;
	}
	
	public Board(int boardSeq, String boardName, User manager) {
		//
		this.boardSeq = boardSeq;
		this.boardName = boardName;
		this.manager = manager;
	}
	
	public Board(String boardName, User manager) {
		//
		this.boardName = boardName;
		this.manager = manager;
	}

	public int getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

}