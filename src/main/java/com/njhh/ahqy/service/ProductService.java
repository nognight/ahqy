package com.njhh.ahqy.service;

import com.njhh.ahqy.entity.Product;
import com.njhh.ahqy.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by HiWin10 on 2017/6/24.
 */
public interface ProductService {
    /**
     * 获取产品表
     * @param Type
     * @param httpSession
     * @return
     */
    List<Product> getProductList(int Type ,HttpSession httpSession);

    /**
     * 获取产品信息
     * @param id
     * @param httpSession
     * @return
     */
    Product  getProductInfo(int id ,HttpSession httpSession);
}
