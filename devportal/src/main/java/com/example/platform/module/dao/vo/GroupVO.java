package com.example.platform.module.dao.vo;

import java.io.Serializable;
import java.util.Date;

import com.example.platform.module.common.extend.entity.SuperEntity;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 作业组实体类
 */
@TableName("t_group")
public class GroupVO extends SuperEntity implements Serializable{

	private static final long serialVersionUID = 2383149812998582257L;
	
	@TableId(type = IdType.AUTO)
	private Integer id;						/*组编码*/
	private String groupName;				/*组名称*/
	@TableField("topic_id")
	private Integer topicID;				/*主题编码*/
	@TableField("level_id")
	private Integer levelID;				/*层级编码*/
	private String cron;					/*调度表达式*/
	private Integer isEnable;				/*是否启用*/
	private String descInfo;				/*描述*/
	private String createUser;				/*创建用户*/
	private Date  createDate;				/*创建时间*/
	private Date  updateDate;				/*最后修改时间*/
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public Integer getLevelID() {
		return levelID;
	}

	public void setLevelID(Integer levelID) {
		this.levelID = levelID;
	}
	
	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public String getDescInfo() {
		return descInfo;
	}

	public void setDescInfo(String descInfo) {
		this.descInfo = descInfo;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Integer getTopicID() {
		return topicID;
	}

	public void setTopicID(Integer topicID) {
		this.topicID = topicID;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
