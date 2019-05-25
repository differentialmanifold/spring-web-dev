package com.example.platform.module.common.response;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * 接口返回数据
 */
public class ResponseResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1206929290920959440L;
	
	private Integer code = -1;
	private String msg = "";
	private Object data = null;
	
	public static final Integer SUCCESS = 0;
	public static final Integer FAIL   = -1;
	
	public ResponseResult(){};

	public ResponseResult(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ResponseResult(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public static ResponseResult success(String msg) {
		return new ResponseResult(SUCCESS, msg);
	}
	
	public static ResponseResult fail(String msg) {
		return new ResponseResult(FAIL, msg);
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


}
