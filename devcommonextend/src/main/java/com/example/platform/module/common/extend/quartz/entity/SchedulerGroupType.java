package com.example.platform.module.common.extend.quartz.entity;


import com.example.platform.module.common.utils.ConstantUtil;

import java.util.ArrayList;
import java.util.List;

public class SchedulerGroupType {

    public static List<SchedulerGroupType> getSchedulerGroupTypeList(String schedulerType, int status) {
        List<SchedulerGroupType> list = new ArrayList<SchedulerGroupType>();
        SchedulerGroupType s1 = new SchedulerGroupType(ConstantUtil.SCHEDULER_TYPE_SYNC, "同步调度", 0);
        SchedulerGroupType s2 = new SchedulerGroupType(ConstantUtil.SCHEDULER_TYPE_OTHER, "其他调度", 0);
        list.add(s1);
        list.add(s2);

        if (null == schedulerType) return list;

        for (SchedulerGroupType type : list) {
            if (schedulerType.equals(type.getName())) {
                type.setStatus(status);
            }
        }
        return list;
    }


    private SchedulerGroupType(String name, String title, int status) {
        this.name = name;
        this.title = title;
        this.status = status;
    }


    private String name;
    private String title;
    private int status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
