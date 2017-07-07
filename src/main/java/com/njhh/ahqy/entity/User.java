package com.njhh.ahqy.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by HiWin10 on 2017/6/14.
 */
public class User implements Serializable {
    private static final long serialVersionUID = -4653417620297446816L ;
    private Integer id;
    private String phoneNum;
    private String name;
    private String passwd;
    private String weCode;
    private Integer channel; //注册渠道 -1是未知 0是启用
    private Integer netType;
    private Integer payType;
    private Integer cityCode;
    private Integer state;//账号状态 -1是停用  0是启用一般会员 1是1级 2是2级依次类推
    private Date lastLogin;//最近登陆

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getWeCode() {
        return weCode;
    }

    public void setWeCode(String weCode) {
        this.weCode = weCode;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getNetType() {
        return netType;
    }

    public void setNetType(Integer netType) {
        this.netType = netType;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", phoneNum='").append(phoneNum).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", passwd='").append(passwd).append('\'');
        sb.append(", weCode='").append(weCode).append('\'');
        sb.append(", channel=").append(channel);
        sb.append(", netType=").append(netType);
        sb.append(", payType=").append(payType);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", state=").append(state);
        sb.append(", lastLogin=").append(lastLogin);
        sb.append('}');
        return sb.toString();
    }
}
