package com.example.platform.module.service;

import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.example.platform.module.dao.dto.GroupDTO;
import com.example.platform.module.dao.vo.GroupVO;

public interface GroupService extends IService<GroupVO> {
	
	GroupVO getByName(String groupName);
	
	GroupVO getById(Integer groupID);

	void add(GroupVO group, String username);
	
	void update(GroupVO group, String username);

	
	void delete(Integer groupID, String username);
	
	/**
	 * Get the group result
	 * @param groupID
	 * @return
	 */
	Map<String, Object> getWithTLNameById(Integer groupID);
	
	/**
	 * Get the group list
	 */
	Page<GroupDTO> getListWithTLName(Page<GroupDTO>page, String groupName, String createUser, Integer topicId, Integer levelId,
									 Integer isEnable);


}
