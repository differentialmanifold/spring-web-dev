package com.example.platform.module.common.quartz.entity;

import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体抽象类
 */
public abstract class AbstractEntity<ID extends Serializable> extends IdEntity<ID> {

	
	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 5049518071585777648L;
	public abstract Date getCreateTime();
	public abstract void setCreateTime(Date createTime);
	public abstract Date getUpdateTime();
	public abstract void setUpdateTime(Date updateTime);
	

	

}
