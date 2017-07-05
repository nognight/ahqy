package com.njhh.ahqy.httpclient.bean;

/**
 * Created by HiWin10 on 2017/6/29.
 */
public class UserInfo {
    /**
     * phone : 13295699645
     * prop : 1
     * serviceType : 01
     * netType : 2
     * feeType : 0
     * cityCode : 305
     * isGroup : false
     * isVip : 0
     * vipLevel : 0
     * brand : 1089
     */

    private String phone;
    private int prop;
    private String serviceType;
    private int netType;
    private int feeType;
    private String cityCode;
    private boolean isGroup;
    private int isVip;
    private int vipLevel;
    private String brand;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getProp() {
        return prop;
    }

    public void setProp(int prop) {
        this.prop = prop;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public int getNetType() {
        return netType;
    }

    public void setNetType(int netType) {
        this.netType = netType;
    }

    public int getFeeType() {
        return feeType;
    }

    public void setFeeType(int feeType) {
        this.feeType = feeType;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public boolean isIsGroup() {
        return isGroup;
    }

    public void setIsGroup(boolean isGroup) {
        this.isGroup = isGroup;
    }

    public int getIsVip() {
        return isVip;
    }

    public void setIsVip(int isVip) {
        this.isVip = isVip;
    }

    public int getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
