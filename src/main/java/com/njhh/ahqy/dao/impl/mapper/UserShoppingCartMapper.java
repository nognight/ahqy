package com.njhh.ahqy.dao.impl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.njhh.ahqy.entity.UserShoppingCart;

@Mapper
public interface UserShoppingCartMapper {
    int insert(@Param("pojo") UserShoppingCart pojo);

    int insertSelective(@Param("pojo") UserShoppingCart pojo);

    int insertList(@Param("pojos") List<UserShoppingCart> pojo);

    int update(@Param("pojo") UserShoppingCart pojo);

    UserShoppingCart getCart(@Param("userId") Integer userId);

}
