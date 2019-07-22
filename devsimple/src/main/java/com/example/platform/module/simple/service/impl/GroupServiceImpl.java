package com.example.platform.module.simple.service.impl;


import com.example.platform.module.simple.service.GroupService;
import com.example.platform.module.simple.vo.GroupVO;
import org.springframework.stereotype.Service;

/**
 */
@Service("groupService")
public class GroupServiceImpl implements GroupService {

    @Override
    public GroupVO getById(Integer groupId) {
        GroupVO groupVO = new GroupVO();

        groupVO.setId(groupId);
        groupVO.setGroupName("simple test group");

        return groupVO;
    }

}
