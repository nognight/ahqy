package com.njhh.ahqy.entity;

import java.util.Date;

/**
 * Created by HiWin10 on 2017/6/26.
 */
public class UserCoupon {
    private Integer id;
    private Integer userId;
    private Integer couponId;
    private Integer source;//来源
    private Integer status;//-1 失效 0可用 1已经使用 2未激活,3部分使用
    private Date getTime;
    private Date startTime;
    private Date expireTime;
    private Date usedTime;
    private String remark;

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

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
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

    public Date getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "UserCoupon{" +
                "id=" + id +
                ", userId=" + userId +
                ", couponId=" + couponId +
                ", source=" + source +
                ", status=" + status +
                ", getTime=" + getTime +
                ", startTime=" + startTime +
                ", expireTime=" + expireTime +
                ", usedTime=" + usedTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
