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
        try
        {
            return productMapper.insert(pojo);
        } catch (Exception e)
        {
            logger.warn("err insert");
            return -1;
        }
        
    }

    public int insertSelective(Product pojo){
        try
        {
            return productMapper.insertSelective(pojo);
        } catch (Exception e)
        {
            logger.warn("err insert");
            return -1;
        }
    }

    public int insertList(List<Product> pojos){
        try
        {
            return productMapper.insertList(pojos);
        } catch (Exception e)
        {
            logger.warn("err insertList");
            return -1;
        }  
    }

    public int update(Product pojo){
        try
        {
            return productMapper.update(pojo);
        } catch (Exception e)
        {
            logger.warn("err update");
            return -1;
        }
    }

    @Override
    public Product getProductById(Product product){
        try
        {
            return  productMapper.getProductById(product);
        } 
        catch (Exception e)
        {
            logger.warn("err getProductById");
            return null;
        }    
    }

    @Override
    public List<Product> getProductList(Product pojo)
    {
        try
        {
            return productMapper.getProductList(pojo);
        } catch (Exception e)
        {
            logger.warn("err getProductList");
            return null;
        }

    }

}
