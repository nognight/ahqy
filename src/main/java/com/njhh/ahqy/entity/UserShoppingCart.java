package com.njhh.ahqy.entity;

/**
 * Created by HiWin10 on 2017/6/28.
 */
public class UserShoppingCart {
    private Integer id;
    private Integer userId;
    private Integer type;//类型 1是产品 2是卡券
    private String goodsIds;//商品ID。对应产品id，或者卡券id
    private String totalPrice;
    private Integer discountType;//打折类型 1是百分比折扣 2是满减
    private String discountPrice;//折扣价格 90可以是9折，或者90元

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(String goodsIds) {
        this.goodsIds = goodsIds;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
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
}
