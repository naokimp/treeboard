package com.namoo.board.domain;

import java.util.Date;

/**
 * @author Administrator
 * @version 1.0
 * @created 02-4-2014 ���� 5:16:52
 */
public class Reply {

	private int replySeq;
	private Date regDate;
	private String content;
	private User author;
	
	public Reply(int replySeq, Date regDate, String content, User author) {
		//
		this.replySeq = replySeq;
		this.regDate = regDate;
		this.content = content;
		this.author = author;
	}
	
	public Reply(String content, User author) {
		//
		this.content = content;
		this.author = author;
	}
	
	public Reply() {
		//
	}

	public Reply(int replySeq, String content, User author) {
		//
		this.replySeq = replySeq;
		this.content = content;
		this.author = author;
	}

	public int getReplySeq() {
		return replySeq;
	}

	public void setReplySeq(int replySeq) {
		this.replySeq = replySeq;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
}