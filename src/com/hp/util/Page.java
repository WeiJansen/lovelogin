package com.hp.util;

import java.util.List;

public class Page {
	// 将分页查询的数据以及分页信息 一块返回到前台页面中
	
	private int count ;		// 一页显示的记录条数
	private int pageNum;	// 当前页码
	private int maxNum;		// 最大页码
	private List list;		// 查询到的数据
	private int sum;       //数据总数
	
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	
}
