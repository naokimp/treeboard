package com.namoo.board.domain;

import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @created 02-4-2014 ���� 5:16:52
 */
public class Article {

	private int articleSeq;
	private String content;
	private Date regDate;
	private String title;
	private List<Reply> replies;
	private User author;

	public Article(int articleSeq, String content, Date regDate, String title, List<Reply> replies, User author){
		//
		this.articleSeq = articleSeq;
		this.content = content;
		this.regDate = regDate;
		this.title = title;
		this.replies = replies;
		this.author = author;
	}
	
	public Article(int articleSeq, String content, Date regDate, String title, User author){
		//
		this.articleSeq = articleSeq;
		this.content = content;
		this.regDate = regDate;
		this.title = title;
		this.author = author;
	}
	
	public Article(String content, String title, User author){
		//
		this.content = content;
		this.title = title;
		this.author = author;
	}
	
	public Article() {
		//
	}

	public int getArticleSeq() {
		return articleSeq;
	}

	public void setArticleSeq(int articleSeq) {
		this.articleSeq = articleSeq;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

}