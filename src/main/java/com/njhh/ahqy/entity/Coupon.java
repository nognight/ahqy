package com.njhh.ahqy.entity;


import java.util.Date;

/**
 * Created by HiWin10 on 2017/6/26.
 */

public class Coupon{
    private Integer id;
    private Integer type;//类型 1是流量券 2是折扣券
    private String name;
    private String description;
    private Integer status;//状态 -1是失效  0是可用
    private Integer isTransferable;//是否可以转让  0是可转让
    private Integer isPurchasable;//是否可购买 0是可购买
    private Integer retailPrice;//售价
    private Integer retailType;//售价类型，0是人民币,1是积分'
    private Date onlineDate;
    private Date offlineDate;
    private String picUrl;
    private String productIds;//流量产品id  多个用 | 分割
    private Integer payType;//计费类型, -1 为未知，0表示通用，1 为预付费，2 为后付费'
    private Integer netType;//-1 表示未知,0表示通用， 1 表示针对 2G 网络, 2 表示针对 3G, 3 表示针对 4G',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsTransferable() {
        return isTransferable;
    }

    public void setIsTransferable(Integer isTransferable) {
        this.isTransferable = isTransferable;
    }

    public Integer getIsPurchasable() {
        return isPurchasable;
    }

    public void setIsPurchasable(Integer isPurchasable) {
        this.isPurchasable = isPurchasable;
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

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Coupon{");
        sb.append("id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", status=").append(status);
        sb.append(", isTransferable=").append(isTransferable);
        sb.append(", isPurchasable=").append(isPurchasable);
        sb.append(", retailPrice=").append(retailPrice);
        sb.append(", retailType=").append(retailType);
        sb.append(", onlineDate=").append(onlineDate);
        sb.append(", offlineDate=").append(offlineDate);
        sb.append(", picUrl='").append(picUrl).append('\'');
        sb.append(", productIds='").append(productIds).append('\'');
        sb.append(", payType=").append(payType);
        sb.append(", netType=").append(netType);
        sb.append('}');
        return sb.toString();
    }
}
