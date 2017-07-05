package com.njhh.ahqy.controller.restfulBeans;

import com.njhh.ahqy.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by HiWin10 on 2017/6/26.
 */
public class LoginBean extends Content {
    private int status;
    private String message = null ;


    public LoginBean(int status) {
        this.status = status;
    }

    public LoginBean(int status, String message) {
        this.status = status;
        this.message = message;
    }



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
