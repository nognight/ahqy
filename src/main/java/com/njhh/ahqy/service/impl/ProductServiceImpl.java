package com.njhh.ahqy.service.impl;

import com.njhh.ahqy.dao.ProductDao;
import com.njhh.ahqy.entity.Product;
import com.njhh.ahqy.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by HiWin10 on 2017/6/28.
 */
@Service
public class ProductServiceImpl implements ProductService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProduct(int id) {
        return null;
    }

    @Override
    public List<Product> getProductList(int type, HttpSession httpSession) {
        Product product = new Product();
        product.setType(type);
        product.setStatus(1);
        return productDao.getProductList(product);
    }
}
