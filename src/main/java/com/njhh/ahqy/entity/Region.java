package com.njhh.ahqy.entity;

/**
 * Created by HiWin10 on 2017/6/29.
 */
public class Region {
    private Integer id;
    private String numSeg;
    private String city;
    private Integer cityCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumSeg() {
        return numSeg;
    }

    public void setNumSeg(String numSeg) {
        this.numSeg = numSeg;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }
}
