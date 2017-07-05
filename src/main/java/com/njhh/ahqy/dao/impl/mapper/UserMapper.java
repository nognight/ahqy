package com.njhh.ahqy.dao.impl.mapper;

import com.njhh.ahqy.entity.Region;
import com.njhh.ahqy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by HiWin10 on 2017/6/14.
 */

@Mapper
public interface UserMapper {
    int insert(@Param("pojo") User pojo);

    int insertSelective(@Param("pojo") User pojo);

    int insertList(@Param("pojos") List<User> pojo);

    int update(@Param("pojo") User pojo);

    User getUserByPhoneNum(@Param("phoneNum") String phoneNum);
    User getUserById(@Param("id") Integer id);
    User getUserByName(@Param("name") String name);

    /**
     * 加载所有的号段列表
     */
    public List<Region> loadAllRegion();
}
