package com.example.platform.module.common.response;

import com.example.platform.module.common.enumtype.ResultEnum;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * 接口返回数据
 */
public class ResponseResult<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1206929290920959440L;

    private Integer code = -1;
    private String msg = "";
    private T data = null;

    public static final Integer SUCCESS = 0;
    public static final Integer FAIL = -1;

    public ResponseResult() {
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg) {
        this(code, msg, null);
    }

    public ResponseResult(ResultEnum resultEnum, T data) {
        this(resultEnum.getCode(), resultEnum.getMsg(), data);
    }

    public ResponseResult(ResultEnum resultEnum) {
        this(resultEnum.getCode(), resultEnum.getMsg());
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

    public void setData(T data) {
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
