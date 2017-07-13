package com.njhh.ahqy.entity;

import java.util.Date;

/**
 * 通用权益
 * Created by HiWin10 on 2017/6/26.
 */
public class Privilege {
    private Integer id;
    private String name;
    private String description;
    private Integer status;
    private Date onlineDate;
    private Date offlineDate;
    private String picUrl;
    private Integer type;//权益类型  1是订购送  2是打折  4是首月免费
    private Integer category;//种类，即类型的子分类  1视频 2英语，3阅读 4游戏
    private String couponIds;//如果是卡券 卡券id | 分割 多个就是组合订购
    private String productIds;//如果是流量包  产品id | 分割
    private Integer netType;
    private Integer payType;
    private Integer discountType;//打折类型 1是百分比折扣 2是满减
    private String discountPrice;//折扣价格 90可以是9折，或者90元
    private Integer giftType;//礼物类型 1是卡券，2是产品  -1是无礼物赠送
    private String giftId;//礼物Id | 分割

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getCouponIds() {
        return couponIds;
    }

    public void setCouponIds(String couponIds) {
        this.couponIds = couponIds;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
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

    public Integer getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Integer discountType) {
        this.discountType = discountType;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getGiftType() {
        return giftType;
    }

    public void setGiftType(Integer giftType) {
        this.giftType = giftType;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Privilege{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", status=").append(status);
        sb.append(", onlineDate=").append(onlineDate);
        sb.append(", offlineDate=").append(offlineDate);
        sb.append(", picUrl='").append(picUrl).append('\'');
        sb.append(", type=").append(type);
        sb.append(", category=").append(category);
        sb.append(", couponIds='").append(couponIds).append('\'');
        sb.append(", productIds='").append(productIds).append('\'');
        sb.append(", netType=").append(netType);
        sb.append(", payType=").append(payType);
        sb.append(", discountType=").append(discountType);
        sb.append(", discountPrice='").append(discountPrice).append('\'');
        sb.append(", giftType=").append(giftType);
        sb.append(", giftId='").append(giftId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
