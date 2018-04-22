package com.mmall.common;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

public class Msg implements Serializable{

    // 是否成功
    private boolean success;
    // 描述信息
    private String msg;
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private Object data;
    // 结果提示码
    private Integer code;



    public static Msg createSucMsg() {
        Msg msg = new Msg();
        msg.setSuccess(true);
        msg.setCode(0);
        msg.setMsg("ok");
        return msg;
    }

    public static Msg createSucMsg(Integer code, Object data) {
        Msg msg = new Msg();
        msg.setSuccess(true);
        msg.setData(data);
        msg.setCode(code);
        msg.setMsg("ok");
        return msg;
    }

    public static Msg createSucMsg(Object data, String desc) {
        Msg msg = new Msg();
        msg.setSuccess(true);
        msg.setData(data);
        msg.setCode(0);
        msg.setMsg(desc);
        return msg;
    }

    public static Msg createMsg(Object data, String desc) {
        Msg msg = new Msg();
        msg.setSuccess(true);
        msg.setData(data);
        msg.setCode(0);
        msg.setMsg(desc);
        return msg;
    }

    public static Msg createFailMsg(Integer code, String desc) {
        Msg msg = new Msg();
        msg.setSuccess(false);
        msg.setMsg(desc);
        msg.setCode(code);
        return msg;
    }

    public static Msg createSucMsg(Object data) {
        Msg msg = new Msg();
        msg.setSuccess(true);
        msg.setData(data);
        msg.setCode(0);
        msg.setMsg("ok");
        return msg;
    }

    public static Msg createFailMsg(String desc) {
        Msg msg = new Msg();
        msg.setSuccess(false);
        msg.setMsg(desc);
        msg.setCode(-1);
        return msg;
    }

    public static Msg createFailMsg(Result result) {
        Msg msg = new Msg();
        msg.setSuccess(false);
        msg.setMsg(result.getLabel());
        msg.setCode(result.getCode());
        return msg;
    }

    public static Msg createSucMsg(Result result) {
        Msg msg = new Msg();
        msg.setSuccess(true);
        msg.setMsg(result.getLabel());
        msg.setCode(result.getCode());
        return msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
}
