package com.example.platform.module.mybatis.proj.service;

import com.example.platform.module.mybatis.entity.TGroup;

import java.util.List;

/**
 */
public interface GroupService extends IService<TGroup> {

    /**
     * 根据条件分页查询
     *
     * @param group
     * @param page
     * @param rows
     * @return
     */
    List<TGroup> selectByGroup(TGroup group, int page, int rows);

}
