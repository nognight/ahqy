package com.njhh.ahqy.dao;

import com.njhh.ahqy.entity.SmsRecord;

/**
 * Created by HiWin10 on 2017/7/4.
 */
public interface SmsRecordDao {
    /**
     * 添加短信入库
     * @param smsRecord
     * @return
     */
    int addRecord(SmsRecord smsRecord);
}
