package com.webshw.domain;

import java.util.Date;

public class ReplyVO {
	private int no;
	private int bno;
	private String replytext;
	private String replyer;
	private Date regdate;
	private Date updatedate;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getReplytext() {
		return replytext;
	}
	public void setReplytext(String replytext) {
		this.replytext = replytext;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public ReplyVO(int no, int bno, String replytext, String replyer, Date regdate, Date updatedate) {
		super();
		this.no = no;
		this.bno = bno;
		this.replytext = replytext;
		this.replyer = replyer;
		this.regdate = regdate;
		this.updatedate = updatedate;
	}
	public ReplyVO() {
		super();
	}
	@Override
	public String toString() {
		return "ReplyVO [no=" + no + ", bno=" + bno + ", replytext=" + replytext + ", replyer=" + replyer + ", regdate="
				+ regdate + ", updatedate=" + updatedate + "]";
	}
	
	
}
