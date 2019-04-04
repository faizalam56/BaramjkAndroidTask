package com.example.baramjktask.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("details")
    @Expose
    private LoginDetails details;
    @SerializedName("request")
    @Expose
    private LoginRequest request;

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

    public LoginDetails getDetails() {
        return details;
    }

    public void setDetails(LoginDetails details) {
        this.details = details;
    }

    public LoginRequest getRequest() {
        return request;
    }

    public void setRequest(LoginRequest request) {
        this.request = request;
    }
}
