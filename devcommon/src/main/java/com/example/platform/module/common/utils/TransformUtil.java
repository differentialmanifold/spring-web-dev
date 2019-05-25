package com.example.platform.module.common.utils;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.platform.module.common.entity.PageRecords;

/**
 */
public class TransformUtil {
	/**
	 * @param str
	 * @return
	 */
	public static Integer getInteger(String str) {
		return str == null ? null : Integer.valueOf(str);
	}
	
	
	/**
	 * @param <T>
	 * @param pageRecords
	 * @param page
	 * @param current
	 * @param size			
	 * @return
	 */
	public static <T> PageRecords<T> getPageRecords(PageRecords<T> pageRecords, Page<T> page, int current, int size) {
		pageRecords.setRecords(page.getRecords());
    	pageRecords.setCurrent(current);
    	pageRecords.setSize(size);
    	pageRecords.setTotal(page.getTotal());
    	pageRecords.setPages(page.getPages());
    	
    	return pageRecords;
	}
	
}
