package com.njhh.ahqy.dao.impl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.njhh.ahqy.entity.Product;

@Mapper
public interface ProductMapper {
    int insert(@Param("pojo") Product pojo);

    int insertSelective(@Param("pojo") Product pojo);

    int insertList(@Param("pojos") List<Product> pojo);

    int update(@Param("pojo") Product pojo);

    Product getProductById(@Param("pojo") Product product);

    List<Product> getProductList(@Param("pojo") Product pojo);
}
