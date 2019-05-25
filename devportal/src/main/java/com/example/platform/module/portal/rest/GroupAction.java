package com.example.platform.module.portal.rest;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.platform.module.common.action.BaseAction;
import com.example.platform.module.common.config.AppProperty;
import com.example.platform.module.common.entity.PageRecords;
import com.example.platform.module.common.exception.BadRequestException;
import com.example.platform.module.common.exception.ForbiddenException;
import com.example.platform.module.common.exception.ResourceNotFoundException;
import com.example.platform.module.common.response.ResponseResult;
import com.example.platform.module.common.utils.TransformUtil;
import com.example.platform.module.common.utils.UserDetailUtil;
import com.example.platform.module.dao.dto.GroupDTO;
import com.example.platform.module.dao.vo.GroupVO;
import com.example.platform.module.portal.thread.task.RunJobTask;
import com.example.platform.module.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.example.platform.module.common.response.ResponseResult.SUCCESS;
import static com.example.platform.module.common.utils.GlobalContainer.threadPool;


/**
 * Job Group
 */
@Controller
@RequestMapping(value = "/groups")
public class GroupAction extends BaseAction {
	
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
    
    
    @RequestMapping(value = "/{id}", method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> getGroup(@PathVariable Integer id) {
    	
    	Map<String, Object> group = groupService.getWithTLNameById(id);
        if(group == null){
        	throw new ResourceNotFoundException("id为"+id+"的作业组不存在");
        }
        
        return group;
    }
     
    
    @RequestMapping(value = "", method = { RequestMethod.GET })
    @ResponseBody
    public PageRecords<GroupDTO> getGroups(@RequestParam(required=false, defaultValue="1") int current,
                                           @RequestParam(required=false, defaultValue="10") int size,
                                           @RequestParam(value="groupName",required=false) String groupName,
                                           @RequestParam(value="createUser",required=false) String createUser,
                                           @RequestParam(value="topicId",required=false, defaultValue="0") Integer topicId,
                                           @RequestParam(value="levelId",required=false, defaultValue="0") Integer levelId,
                                           @RequestParam(value="isEnable",required=false, defaultValue="-1") Integer isEnable) {
    	
    	Page<GroupDTO> page = new Page<GroupDTO>(current, size);
    	
    	Page<GroupDTO> jobPage = groupService.getListWithTLName(page, groupName, createUser,
    			topicId, levelId, isEnable);
    	
    	return TransformUtil.getPageRecords(new PageRecords<GroupDTO>(), jobPage, current, size);
    }

    @RequestMapping(value = "/user/list", method = { RequestMethod.GET })
    @ResponseBody
    public List<GroupVO> getMyGroups() {
    	
    	List<GroupVO> groupList = groupService.selectList(new EntityWrapper<GroupVO>()
    			.eq("create_user", UserDetailUtil.getUserName())
    			.orderBy("id", false));
    	
    	return groupList;
    }

    
    @RequestMapping(value = "", method = { RequestMethod.POST })
    @ResponseBody
    public ResponseResult addGroup(@RequestBody GroupVO groupRequest) {

        
    	groupRequest.setGroupName(groupRequest.getGroupName().trim());
        GroupVO group = groupService.getByName(groupRequest.getGroupName());
        
        if (group != null) {
        	throw new BadRequestException("工作组名称已存在");
        }
        
        groupService.add(groupRequest, UserDetailUtil.getUserName());
        
        return new ResponseResult(SUCCESS , "添加作业组成功", groupRequest);
        
    }
    
    
    @RequestMapping(value = "", method = { RequestMethod.PUT })
    @ResponseBody
    public ResponseResult editGroup(@RequestBody GroupVO groupRequest) {
    	
    	GroupVO group = groupService.getById(groupRequest.getId());
    	
    	if (group == null) {
    		throw new ResourceNotFoundException("没有ID为"+groupRequest.getId()+"对应的工作组存在");
    	}
    	
    	if (!group.getCreateUser().equals(UserDetailUtil.getUserName())) {
			throw new ForbiddenException("只有作业组创建人可以编辑作业组");
		}
    	
    	groupRequest.setGroupName(groupRequest.getGroupName().trim());
    	
    	groupService.update(groupRequest, UserDetailUtil.getUserName());
    	
    	return new ResponseResult(SUCCESS , "编辑作业组成功");
    }
    


    
    @RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
    @ResponseBody
    public ResponseResult deleteGroup(@PathVariable Integer id) {
    	
    	GroupVO group = groupService.getById(id);
    	
    	if (group == null) {
    		throw new ResourceNotFoundException("没有ID为"+id+"对应的工作组存在");
    	}
    	
    	if (!group.getCreateUser().equals(UserDetailUtil.getUserName())) {
			throw new ForbiddenException("只有作业组创建人可以删除作业组");
		}
		
		groupService.delete(id, UserDetailUtil.getUserName());
    	
    	return new ResponseResult(SUCCESS , "删除作业组成功");
    }


    @RequestMapping(value = "/runjob", method = { RequestMethod.GET })
    @ResponseBody
    public ResponseResult runjob() {
        Runnable runJobTask = new RunJobTask(appProperty);
        threadPool.execute(runJobTask);

        return new ResponseResult(SUCCESS , "运行成功");
    }




}
