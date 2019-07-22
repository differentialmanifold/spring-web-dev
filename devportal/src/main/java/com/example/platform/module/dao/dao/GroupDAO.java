package com.example.platform.module.dao.dao;

import java.util.ArrayList;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.example.platform.module.common.extend.dao.SuperDAO;
import com.example.platform.module.dao.vo.GroupVO;

public interface GroupDAO extends SuperDAO<GroupVO> {
	
	GroupVO get(Map<String, Object> params);
	
	void add(GroupVO group);
	
	void update(GroupVO group);
	
	void delete(Integer groupID);
	
	
	/**
	 * Get the group
	 * @param params
	 * @return
	 */
	Map<String, Object> getWithTLName(Map<String, Object> params);
	
	
	/**
	 * Get the group list
	 * @param params
	 * @return
	 */
	ArrayList<Map<String, Object>> getListWithTLName(Pagination page, Map<String, Object> params);


}
