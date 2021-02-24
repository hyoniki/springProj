package com.webshw.domain;

public class PagingCriteria {
	private int page; // 시작 페이지
	private int perPageNum; // 한 페이지당 출력 하는 글 갯수
	
	public PagingCriteria() {
		this.page = 1;
		this.perPageNum = 20;
	}
	
	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	
	public void setPerPageNum(int perPageNum) {
		if (perPageNum <= 0 || perPageNum > 81) {
			this.perPageNum = 20;
			return;
		}
		this.perPageNum = perPageNum;
	}
	
	public int getPageStart() {
		return (this.page - 1) * 20;
	}
	
	public int getPerPageNum() {
		return this.perPageNum;
	}
	
	public int getPage() {
		return this.page;
	}

	@Override
	public String toString() {
		return "PagingCriteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
	
}
