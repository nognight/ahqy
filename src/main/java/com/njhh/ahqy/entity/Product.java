package com.njhh.ahqy.entity;

import java.util.Date;

/**
 * Created by HiWin10 on 2017/6/24.
 */
public class Product {

    private Integer id;
    private String code;//产品订购code    4G 90267757$51930909$8209022  用于分割3个参数，空传0     23g: 9619$$00
    private Integer type;//产品类型，定义 1省内包月 2国内包月 3半年包 4闲时 5sp 6日包 7加油包 8首月0',
    private Integer status;//状态。0生效
    private String name;
    private String description;
    private String memo;
    private Date onlineDate;
    private Date offlineDate;
    private String picUrl;
    private Integer retailPrice;//售价
    private Integer retailType;//售价类型，0是包月,1是半年，2是包天'
    private Integer source;//来源平台，当前定义: -1 表示未知平台, 1 安徽BSS平台（xyl）, 2 信息化接口, 3 总台智能客服接口, 4 科大讯飞4G半年包，5 AOPSP接口 ，6融合订购接口'
    private Integer supplierId;//供应商标识
    private String supportBand;//支持品牌
    private Integer isUnsubscribe;//能否退订
    private Integer isRepeatable;//能否重复订购 0为可以 其他值不能
    private Integer payType;//计费类型, -1 为未知，0表示通用，1 为预付费，2 为后付费'
    private Integer netType;//-1 表示未知,0表示23G通用， 1 表示针对 2G 网络, 2 表示针对 3G, 3 表示针对 4G,
    private Integer hasCodes;//是否拥有多个code，0表示多个
    private String codes;//多个code | 分割  常见于加油包

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getOnlineDate() {
        return onlineDate;
    }

    public void setOnlineDate(Date onlineDate) {
        this.onlineDate = onlineDate;
    }

    public Date getOfflineDate() {
        return offlineDate;
    }

    public void setOfflineDate(Date offlineDate) {
        this.offlineDate = offlineDate;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Integer retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Integer getRetailType() {
        return retailType;
    }

    public void setRetailType(Integer retailType) {
        this.retailType = retailType;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupportBand() {
        return supportBand;
    }

    public void setSupportBand(String supportBand) {
        this.supportBand = supportBand;
    }

    public Integer getIsUnsubscribe() {
        return isUnsubscribe;
    }

    public void setIsUnsubscribe(Integer isUnsubscribe) {
        this.isUnsubscribe = isUnsubscribe;
    }

    public Integer getIsRepeatable() {
        return isRepeatable;
    }

    public void setIsRepeatable(Integer isRepeatable) {
        this.isRepeatable = isRepeatable;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getNetType() {
        return netType;
    }

    public void setNetType(Integer netType) {
        this.netType = netType;
    }

    public Integer getHasCodes() {
        return hasCodes;
    }

    public void setHasCodes(Integer hasCodes) {
        this.hasCodes = hasCodes;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", memo='").append(memo).append('\'');
        sb.append(", onlineDate=").append(onlineDate);
        sb.append(", offlineDate=").append(offlineDate);
        sb.append(", picUrl='").append(picUrl).append('\'');
        sb.append(", retailPrice=").append(retailPrice);
        sb.append(", retailType=").append(retailType);
        sb.append(", source=").append(source);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", supportBand='").append(supportBand).append('\'');
        sb.append(", isUnsubscribe=").append(isUnsubscribe);
        sb.append(", isRepeatable=").append(isRepeatable);
        sb.append(", payType=").append(payType);
        sb.append(", netType=").append(netType);
        sb.append(", hasCodes=").append(hasCodes);
        sb.append(", codes='").append(codes).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
