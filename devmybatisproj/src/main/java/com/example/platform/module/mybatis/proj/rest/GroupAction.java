package com.example.platform.module.mybatis.proj.rest;


import com.example.platform.module.common.enumtype.ResultEnum;
import com.example.platform.module.common.response.ResponseResult;
import com.example.platform.module.mybatis.entity.TGroup;
import com.example.platform.module.mybatis.proj.service.GroupService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Job Group
 */
@Controller
@RequestMapping(value = "/groups")
public class GroupAction {

    private static final Logger logger = LoggerFactory.getLogger(GroupAction.class);

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String ok() {

        return "It's OK , process is running !";
    }

    @RequestMapping(value = {"/list"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseResult getList(@RequestBody TGroup group,
                                  @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        List<TGroup> groupList = groupService.selectByGroup(group, pageNum, pageSize);
        PageInfo pageInfo = new PageInfo<TGroup>(groupList);
        return new ResponseResult<PageInfo>(ResultEnum.SUCCESS, pageInfo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getGroupById(@PathVariable Integer id) {
        TGroup group = null;
        if (id != null) {
            group = groupService.selectByKey(id);
        }
        return new ResponseResult<TGroup>(ResultEnum.SUCCESS, group);

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult save(@RequestBody TGroup group) {
        if (group.getId() != null) {
            groupService.updateAll(group);
        } else {
            groupService.save(group);
        }
        return ResponseResult.success("保存成功");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseResult delete(@PathVariable Integer id) {
        groupService.delete(id);
        return ResponseResult.success("删除成功");
    }
}
