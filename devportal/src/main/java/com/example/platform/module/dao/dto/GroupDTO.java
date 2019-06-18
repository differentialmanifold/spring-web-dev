package com.example.platform.module.dao.dto;

import java.io.Serializable;
import java.util.Map;



/**
 *
 */
public class GroupDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 831974903426037545L;
	
	
	private Map<String, Object> group;

	
	public Map<String, Object> getGroup() {
		return group;
	}

	public void setGroup(Map<String, Object> group) {
		this.group = group;
	}
	
	

}
