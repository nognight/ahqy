package com.njhh.ahqy.entity;

import java.util.Date;

/**
 * Created by HiWin10 on 2017/6/26.
 */
public class CheckIn {
    private Integer id;
    private Integer userId;
    private Date checkInDate;
    private Integer getScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CheckIn{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", checkInDate=").append(checkInDate);
        sb.append(", getScore=").append(getScore);
        sb.append('}');
        return sb.toString();
    }

}
