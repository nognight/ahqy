package com.njhh.ahqy.dao.impl;

import com.njhh.ahqy.dao.ProductDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.njhh.ahqy.entity.Product;
import com.njhh.ahqy.dao.impl.mapper.ProductMapper;

@Repository
public class ProductDaoImpl implements ProductDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = false)
    private ProductMapper productMapper;

    public int insert(Product pojo){
        return productMapper.insert(pojo);
    }

    public int insertSelective(Product pojo){
        return productMapper.insertSelective(pojo);
    }

    public int insertList(List<Product> pojos){
        return productMapper.insertList(pojos);
    }

    public int update(Product pojo){
        return productMapper.update(pojo);
    }

    @Override
    public Product getProductById(Product product){
        return  productMapper.getProductById(product);
    }
    @Override
    public List<Product> getProductList(Product pojo){


        return productMapper.getProductList(pojo);
    }

}
