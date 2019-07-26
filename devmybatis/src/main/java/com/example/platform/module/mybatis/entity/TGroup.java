package com.example.platform.module.mybatis.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_group")
public class TGroup {
    /**
     * 组编码
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 组名称
     */
    @Column(name = "group_name")
    private String groupName;

    /**
     * 主题编码
     */
    @Column(name = "topic_id")
    private Integer topicId;

    /**
     * 层级编码
     */
    @Column(name = "level_id")
    private Integer levelId;

    /**
     * 调度表达式
     */
    private String cron;

    /**
     * 是否启用
     */
    @Column(name = "is_enable")
    private Boolean isEnable;

    /**
     * 备注
     */
    @Column(name = "desc_info")
    private String descInfo;

    /**
     * 创建用户
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 最后修改时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 获取组编码
     *
     * @return id - 组编码
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置组编码
     *
     * @param id 组编码
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取组名称
     *
     * @return group_name - 组名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置组名称
     *
     * @param groupName 组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 获取主题编码
     *
     * @return topic_id - 主题编码
     */
    public Integer getTopicId() {
        return topicId;
    }

    /**
     * 设置主题编码
     *
     * @param topicId 主题编码
     */
    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    /**
     * 获取层级编码
     *
     * @return level_id - 层级编码
     */
    public Integer getLevelId() {
        return levelId;
    }

    /**
     * 设置层级编码
     *
     * @param levelId 层级编码
     */
    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    /**
     * 获取调度表达式
     *
     * @return cron - 调度表达式
     */
    public String getCron() {
        return cron;
    }

    /**
     * 设置调度表达式
     *
     * @param cron 调度表达式
     */
    public void setCron(String cron) {
        this.cron = cron;
    }

    /**
     * 获取是否启用
     *
     * @return is_enable - 是否启用
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * 设置是否启用
     *
     * @param isEnable 是否启用
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * 获取备注
     *
     * @return desc_info - 备注
     */
    public String getDescInfo() {
        return descInfo;
    }

    /**
     * 设置备注
     *
     * @param descInfo 备注
     */
    public void setDescInfo(String descInfo) {
        this.descInfo = descInfo;
    }

    /**
     * 获取创建用户
     *
     * @return create_user - 创建用户
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建用户
     *
     * @param createUser 创建用户
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取最后修改时间
     *
     * @return update_date - 最后修改时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置最后修改时间
     *
     * @param updateDate 最后修改时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}