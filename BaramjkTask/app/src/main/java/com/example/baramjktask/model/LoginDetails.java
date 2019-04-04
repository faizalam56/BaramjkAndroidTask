package com.example.baramjktask.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDetails {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("next_steps")
    @Expose
    private String nextSteps;
    @SerializedName("has_addressbook")
    @Expose
    private Integer hasAddressbook;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("client_name_cookie")
    @Expose
    private String clientNameCookie;
    @SerializedName("contact_phone")
    @Expose
    private String contactPhone;
    @SerializedName("location_name")
    @Expose
    private String locationName;
    @SerializedName("default_address")
    @Expose
    private Boolean defaultAddress;
    @SerializedName("transaction_type")
    @Expose
    private String transactionType;
    @SerializedName("show_mobile_number")
    @Expose
    private Boolean showMobileNumber;
    @SerializedName("social_strategy")
    @Expose
    private String socialStrategy;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNextSteps() {
        return nextSteps;
    }

    public void setNextSteps(String nextSteps) {
        this.nextSteps = nextSteps;
    }

    public Integer getHasAddressbook() {
        return hasAddressbook;
    }

    public void setHasAddressbook(Integer hasAddressbook) {
        this.hasAddressbook = hasAddressbook;
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

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Boolean getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Boolean getShowMobileNumber() {
        return showMobileNumber;
    }

    public void setShowMobileNumber(Boolean showMobileNumber) {
        this.showMobileNumber = showMobileNumber;
    }

    public String getSocialStrategy() {
        return socialStrategy;
    }

    public void setSocialStrategy(String socialStrategy) {
        this.socialStrategy = socialStrategy;
    }
}
