package com.example.baramjktask.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignupResponse {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("details")
    @Expose
    private SignupDetails details;
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

    public SignupDetails getDetails() {
        return details;
    }

    public void setDetails(SignupDetails details) {
        this.details = details;
    }

    public SignupRequest getRequest() {
        return request;
    }

    public void setRequest(SignupRequest request) {
        this.request = request;
    }
}
