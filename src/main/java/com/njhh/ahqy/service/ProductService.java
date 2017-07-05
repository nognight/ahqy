package com.njhh.ahqy.service;

import com.njhh.ahqy.entity.Product;
import com.njhh.ahqy.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by HiWin10 on 2017/6/24.
 */
public interface ProductService {
    Product getProduct(int id);
    List<Product> getProductList(int Type ,HttpSession httpSession);
}
