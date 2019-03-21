package com.njhh.ahqy.entity;

import java.util.Date;

/**
 * 特殊用户权益
 * Created by HiWin10 on 2017/6/27.
 */
public class UserPrivilege {
    private Integer id;
    private Integer userId;
    private Integer privilegeId;
    private Integer source;//来源   1是短信
    private Integer status;//   -2增加权益失败，即订购权益失败   -1失效   0可用,即未赠送   1已经使用即已经赠送   2使用失败，赠送失败  3使用中 -7过期
    private Date getTime;
    private Date startTime;
    private Date expireTime;
    private Date usedTime;
    private String remark; //备注

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

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
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
        final StringBuffer sb = new StringBuffer("UserPrivilege{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", privilegeId=").append(privilegeId);
        sb.append(", source=").append(source);
        sb.append(", status=").append(status);
        sb.append(", getTime=").append(getTime);
        sb.append(", startTime=").append(startTime);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", usedTime=").append(usedTime);
        sb.append(", remark='").append(remark).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
