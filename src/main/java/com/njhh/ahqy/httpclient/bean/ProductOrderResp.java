package com.njhh.ahqy.httpclient.bean;


import java.util.Date;

/**
 * 业务订购操作的响应
 */
public class ProductOrderResp {

    // 存储产品订购接口的返回结果
    private int result;

    // 响应码

    private String respCode;

    // 响应描述

    private String respDesc;

    // 生效日期

    private Date activeDate;

    // 对应于本地的订购日志标识
    public int orderLogId;

    public ProductOrderResp() {
        super();
    }

    public ProductOrderResp(int result) {
        super();
        this.result = result;
    }

    public ProductOrderResp(int result, String respCode, String respDesc,
                            Date activeDate) {
        super();
        this.result = result;
        this.respCode = respCode;
        this.respDesc = respDesc;
        this.activeDate = activeDate;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public Date getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
    }

    @Override
    public String toString() {
        return "ProductOrderResp{" +
                "result=" + result +
                ", respCode='" + respCode + '\'' +
                ", respDesc='" + respDesc + '\'' +
                ", activeDate=" + activeDate +
                ", orderLogId=" + orderLogId +
                '}';
    }
}
