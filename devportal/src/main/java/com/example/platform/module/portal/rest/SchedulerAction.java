package com.example.platform.module.portal.rest;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.platform.module.common.action.BaseAction;
import com.example.platform.module.common.config.AppProperty;
import com.example.platform.module.common.entity.PageRecords;
import com.example.platform.module.common.exception.BadRequestException;
import com.example.platform.module.common.exception.ForbiddenException;
import com.example.platform.module.common.exception.ResourceNotFoundException;
import com.example.platform.module.common.quartz.TaskService;
import com.example.platform.module.common.quartz.entity.DevQuartzJobVO;
import com.example.platform.module.common.response.ResponseResult;
import com.example.platform.module.common.utils.TransformUtil;
import com.example.platform.module.common.utils.UserDetailUtil;
import com.example.platform.module.dao.dto.GroupDTO;
import com.example.platform.module.dao.vo.GroupVO;
import com.example.platform.module.portal.thread.task.RunJobTask;
import com.example.platform.module.service.GroupService;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.example.platform.module.common.response.ResponseResult.SUCCESS;
import static com.example.platform.module.common.utils.GlobalContainer.threadPool;


@Controller
@RequestMapping(value = "/scheduler")
public class SchedulerAction extends BaseAction {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerAction.class);

    @Autowired
    private TaskService taskService;


    @RequestMapping(value = "", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseResult addQuartz(@RequestBody DevQuartzJobVO job) throws SchedulerException {

        taskService.deleteJob(job);
        boolean add = taskService.addJob(job);

        logger.info("Result of add job is {}", add);


        return new ResponseResult(SUCCESS, "添加调度任务成功", add);

    }

}