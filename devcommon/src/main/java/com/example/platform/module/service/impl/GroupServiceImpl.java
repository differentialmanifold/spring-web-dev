package com.example.platform.module.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.example.platform.module.dao.dao.GroupDAO;
import com.example.platform.module.dao.dto.GroupDTO;
import com.example.platform.module.dao.vo.GroupVO;
import com.example.platform.module.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;

/**
 */
@Service("groupService")
public class GroupServiceImpl extends ServiceImpl<GroupDAO, GroupVO> implements GroupService {
	
	@Autowired
	private GroupDAO groupDAO;


	@Override
	public GroupVO getByName(String groupName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupName", groupName);
		
		return groupDAO.get(params);
	}

	@Override
	public GroupVO getById(Integer groupID) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", groupID);
		
		return groupDAO.get(params);
	}
	
	@Override
	@Transactional
	public void add(GroupVO group, String username) {
		groupDAO.insert(group);

	}

	@Override
	@Transactional
	public void update(GroupVO group, String username) {
		groupDAO.update(group);
	}


	@Override
	@Transactional
	public void delete(Integer groupID, String username) {
		GroupVO group = groupDAO.selectById(groupID);
		if(group != null) {
			groupDAO.delete(groupID);

		}
		
	}

	
	@Override
	public Map<String, Object> getWithTLNameById(Integer groupID) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", groupID);
		
		return groupDAO.getWithTLName(params);
	}

	@Override
	public Page<GroupDTO> getListWithTLName(Page<GroupDTO>page, String groupName, String createUser,
											Integer topicId, Integer levelId,
											Integer isEnable) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupName", groupName);
		params.put("createUser", createUser);
		params.put("topicId", topicId);
		params.put("levelId", levelId);
		params.put("isEnable", isEnable);
		
		List<GroupDTO> groupDtoList = new LinkedList<GroupDTO>();
		
		ArrayList<Map<String, Object>> groupList = groupDAO.getListWithTLName(page, params);
		
		if(!CollectionUtils.isEmpty(groupList)) {

			for (Map<String, Object> gp : groupList) {
				
				GroupDTO groupDto = new GroupDTO();
				groupDto.setGroup(gp);

				groupDtoList.add(groupDto);
			}
		}
		
		
		page.setRecords(groupDtoList);
		
		return page;
	}


	
}
