package com.example.baramjktask.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignupResponseFail {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("details")
    @Expose
    private List<Object> details = null;
    @SerializedName("request")
    @Expose
    private SignupRequest request;

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

    public List<Object> getDetails() {
        return details;
    }

    public void setDetails(List<Object> details) {
        this.details = details;
    }

    public SignupRequest getRequest() {
        return request;
    }

    public void setRequest(SignupRequest request) {
        this.request = request;
    }
}
