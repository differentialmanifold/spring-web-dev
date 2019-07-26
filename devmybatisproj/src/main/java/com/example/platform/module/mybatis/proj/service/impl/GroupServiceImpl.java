package com.example.platform.module.mybatis.proj.service.impl;

import com.example.platform.module.mybatis.entity.TGroup;
import com.example.platform.module.mybatis.proj.service.GroupService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 */
@Service("groupService")
public class GroupServiceImpl extends BaseService<TGroup> implements GroupService {

    @Override
    public List<TGroup> selectByGroup(TGroup group, int page, int rows) {
        Example example = new Example(TGroup.class);
        Example.Criteria criteria = example.createCriteria();
//        if (StringUtil.isNotEmpty(country.getCountryname())) {
//            criteria.andLike("countryname", "%" + country.getCountryname() + "%");
//        }
//        if (StringUtil.isNotEmpty(country.getCountrycode())) {
//            criteria.andLike("countrycode", "%" + country.getCountrycode() + "%");
//        }
//        if (country.getId() != null) {
//            criteria.andEqualTo("id", country.getId());
//        }
        //分页查询
//        PageHelper.startPage(page, rows);
        return selectByExample(example);
    }

}
