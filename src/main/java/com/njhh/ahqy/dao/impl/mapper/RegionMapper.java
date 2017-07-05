package com.njhh.ahqy.dao.impl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.njhh.ahqy.entity.Region;

@Mapper
public interface RegionMapper {
    int insert(@Param("pojo") Region pojo);

    int insertSelective(@Param("pojo") Region pojo);

    int insertList(@Param("pojos") List<Region> pojo);

    int update(@Param("pojo") Region pojo);

    List<Region> getRegionList();
    Region getRegion(@Param("pojo") Region pojo);
}
