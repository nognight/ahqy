package com.njhh.ahqy.entity;

import java.util.Date;

/**
 * Created by HiWin10 on 2017/6/26.
 */
public class UserOrder {
    private Integer id;
    private Integer userId;
    private String phoneNum;//订购的号码
    private Integer productId;
    private String code;//订购code
    private Integer subType;//订购类型 0订购 1退订
    private String smsCode;//订购短信码
    private Integer state;
    private Date orderTime;
    private Date backTime;
    private String backCode;
    private Date startTime;
    private Date expireTime;
    private String message;

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    public String getBackCode() {
        return backCode;
    }

    public void setBackCode(String backCode) {
        this.backCode = backCode;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserOrder{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", phoneNum='").append(phoneNum).append('\'');
        sb.append(", productId=").append(productId);
        sb.append(", code='").append(code).append('\'');
        sb.append(", subType=").append(subType);
        sb.append(", smsCode='").append(smsCode).append('\'');
        sb.append(", state=").append(state);
        sb.append(", orderTime=").append(orderTime);
        sb.append(", backTime=").append(backTime);
        sb.append(", backCode='").append(backCode).append('\'');
        sb.append(", startTime=").append(startTime);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }


}
