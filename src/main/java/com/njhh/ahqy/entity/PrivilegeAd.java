package com.njhh.ahqy.entity;

import java.util.Date;

/**
 * Created by HiWin10 on 2017/7/14.
 */
public class PrivilegeAd {
    private Integer id;
    private Integer source;
    private String sourceName;
    private Integer type;
    private String adName;
    private String adUrl;
    private String adPic;
    private String adInfo;
    private Integer status;
    private Date onlineDate;
    private Date offlineDate;
    private String remark;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public String getAdPic() {
        return adPic;
    }

    public void setAdPic(String adPic) {
        this.adPic = adPic;
    }

    public String getAdInfo() {
        return adInfo;
    }

    public void setAdInfo(String adInfo) {
        this.adInfo = adInfo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PrivilegeAd{");
        sb.append("id=").append(id);
        sb.append(", source=").append(source);
        sb.append(", sourceName='").append(sourceName).append('\'');
        sb.append(", type=").append(type);
        sb.append(", adName='").append(adName).append('\'');
        sb.append(", adUrl='").append(adUrl).append('\'');
        sb.append(", adPic='").append(adPic).append('\'');
        sb.append(", adInfo='").append(adInfo).append('\'');
        sb.append(", status=").append(status);
        sb.append(", onlineDate=").append(onlineDate);
        sb.append(", offlineDate=").append(offlineDate);
        sb.append(", remark='").append(remark).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
