package com.wcc.uitl;

import java.util.List;

import com.wcc.daomain.Customer;


public class Page {
	//存放分页记录
	private List<Customer> records;
	//当前页码
	private int currentPageNum; 
	//总记录条数
	private int totalRecords;
	//每页显示的条数 这里我们设置为  每一个设置10 个
	private int pageSize = 5;
	//总页数
	private int  totalPage;
	//每页开始记录的起始下标
	private  int startIndex;
	
	// 用于显示页面中的的页数
	private int startPage;
	private int endPage;
	
	
	public Page(int currentPageNum, int  totalRecords){
		this.currentPageNum = currentPageNum;
		this.totalRecords = totalRecords;
		
		// 可以计算出要显示的总页数
		totalPage = totalRecords%pageSize == 0 ?  totalRecords/pageSize: (totalRecords/pageSize + 1);
		//计算开始的下标
		startIndex = (currentPageNum-1)*pageSize;
		
		if (totalPage <= 5) {
			startPage= 1;
			endPage = totalPage;
		}else {
			startPage = currentPageNum-2;
			endPage = currentPageNum +2;
			if (startPage < 1) {
				startPage = 1;
				endPage = startPage +4;
			}
			if (endPage > totalPage) {
				endPage = totalPage;
				startPage = totalPage- 4;
			}
		}
	}
	public List<Customer> getRecords() {
		return records;
	}

	public void setRecords(List<Customer> records) {
		this.records = records;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	

	public int getStartPage() {
		return startPage;
	}


	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}


	public int getEndPage() {
		return endPage;
	}


	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}



}
