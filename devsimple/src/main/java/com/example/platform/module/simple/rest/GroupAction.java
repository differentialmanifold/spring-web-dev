package com.example.platform.module.simple.rest;


import com.example.platform.module.common.config.AppProperty;
import com.example.platform.module.common.enumtype.ResultEnum;
import com.example.platform.module.common.exception.ForbiddenException;
import com.example.platform.module.common.exception.ResourceNotFoundException;
import com.example.platform.module.common.response.ResponseResult;
import com.example.platform.module.simple.service.GroupService;
import com.example.platform.module.simple.vo.GroupVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Job Group
 */
@Controller
@RequestMapping(value = "/groups")
public class GroupAction {

    private static final Logger logger = LoggerFactory.getLogger(GroupAction.class);

    @Autowired
    private GroupService groupService;

    @Autowired
    private AppProperty appProperty;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String ok() {

        return "It's OK , process is running !";
    }


    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public GroupVO getGroup(@PathVariable Integer id) {

        GroupVO group = groupService.getById(id);
        if (group == null) {
            throw new ResourceNotFoundException("id为" + id + "的作业组不存在");
        }

        return group;
    }

    @RequestMapping(value = "/exception/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseResult groupException(@PathVariable Integer id) {

        if (id == 1) {
            throw new ResourceNotFoundException("id为" + id + "的作业组不存在");
        }

        if (id == 2) {
            throw new ForbiddenException("forbidden request");
        }

        GroupVO groupVO = new GroupVO();

        groupVO.setId(5);

        return new ResponseResult<GroupVO>(ResultEnum.SUCCESS, groupVO);

    }


}
