package com.example.baramjktask.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignupDetails {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("next_step")
    @Expose
    private Object nextStep;
    @SerializedName("is_checkout")
    @Expose
    private Integer isCheckout;
    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("client_name_cookie")
    @Expose
    private String clientNameCookie;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getNextStep() {
        return nextStep;
    }

    public void setNextStep(Object nextStep) {
        this.nextStep = nextStep;
    }

    public Integer getIsCheckout() {
        return isCheckout;
    }

    public void setIsCheckout(Integer isCheckout) {
        this.isCheckout = isCheckout;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getClientNameCookie() {
        return clientNameCookie;
    }

    public void setClientNameCookie(String clientNameCookie) {
        this.clientNameCookie = clientNameCookie;
    }
}
