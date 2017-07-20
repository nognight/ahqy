package com.njhh.ahqy.dao;

import com.njhh.ahqy.entity.Product;

import java.util.List;

/**
 * Created by HiWin10 on 2017/6/24.
 */
public interface ProductDao {
    /**
     *通过id获得产品信息
     * @param product
     * @return
     */
    Product getProductById(Product product);

    /**
     * 获得产品列表
     * @param product
     * @return
     */

    List<Product> getProductList(Product product);

}
