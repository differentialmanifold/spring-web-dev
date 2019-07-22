package com.example.platform.module.common.extend.quartz.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public abstract class IdEntity<ID extends Serializable> implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 3614622551884487496L;

	public abstract ID getId();

	public abstract void setId(ID id);

	@JSONField(serialize = false, deserialize = false)
	public boolean isNew() {
		return null == getId();
	}

	@Override
	public int hashCode() {

		int hashCode = 17;

		hashCode += null == getId() ? 0 : getId().hashCode() * 31;

		return hashCode;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
