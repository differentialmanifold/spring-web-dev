package com.example.platform.module.common.extend.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页返回结果实体
 *
 * @param <T>
 */
public class PageRecords<T> implements Serializable {

	private static final long serialVersionUID = 7002330760220624545L;
	
	public PageRecords(){
		
	}
	
	/**
	 * 当页记录
	 */
	private List<T> records = new ArrayList<T>();
	
	/**
	 * 当前页码
	 */
	private int current = 1;
	/**
	 * 页大小
	 */
	private int size = 10;
	
	/**
	 * 总页数
	 */
	private int pages = 0;
	/**
	 * 符合条件的总数量
	 */
	private long total = 0;

	public List<T> getRecords() {
		return records;
	}



	public void setRecords(List<T> records) {
		this.records = records;
	}



	public int getCurrent() {
		return current;
	}



	public void setCurrent(int current) {
		this.current = current;
	}



	public int getSize() {
		return size;
	}



	public void setSize(int size) {
		this.size = size;
	}



	

	public int getPages() {
		return pages;
	}



	public void setPages(int pages) {
		this.pages = pages;
	}



	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

}
