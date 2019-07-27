package com.example.platform.module.mybatis.proj.service.impl;

import com.example.platform.module.mybatis.entity.TGroup;
import com.example.platform.module.mybatis.proj.service.GroupService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 */
@Service("groupService")
public class GroupServiceImpl extends BaseService<TGroup> implements GroupService {

    @Override
    public List<TGroup> selectByGroup(TGroup group, int pageNum, int pageSize) {
        Example example = new Example(TGroup.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(group.getGroupName())) {
            criteria.andLike("groupName", "%" + group.getGroupName() + "%");
        }
        if (StringUtil.isNotEmpty(group.getDescInfo())) {
            criteria.andLike("descInfo", "%" + group.getDescInfo() + "%");
        }
        if (group.getId() != null) {
            criteria.andEqualTo("id", group.getId());
        }
        // 分页查询
        PageHelper.startPage(pageNum, pageSize);
        return selectByExample(example);
    }

}
