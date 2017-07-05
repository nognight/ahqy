package com.njhh.ahqy.dao;

import com.njhh.ahqy.entity.Product;

import java.util.List;

/**
 * Created by HiWin10 on 2017/6/24.
 */
public interface ProductDao {
    Product getProductById(Product product);

    List<Product> getProductList(Product product);

}
