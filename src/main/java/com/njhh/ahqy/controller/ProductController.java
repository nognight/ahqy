package com.njhh.ahqy.controller;

import com.njhh.ahqy.controller.restfulBeans.ApiBean;
import com.njhh.ahqy.controller.restfulBeans.ObjBean;
import com.njhh.ahqy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


/**
 * Created by HiWin10 on 2017/6/24.
 */
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/api/product/getList")
    private ApiBean getProductList(HttpSession httpSession,
                                         @RequestParam(value = "type", defaultValue = "0")int type){

        ApiBean apiBean = new ApiBean();
        apiBean.setContent(new ObjBean(productService.getProductList(type,httpSession)));
        apiBean.setRet(0);
        return apiBean;
    }

    @RequestMapping("/api/product/getInfo")
    private ApiBean getProductInfo(HttpSession httpSession,
                                   @RequestParam(value = "id", defaultValue = "0")int id){

        ApiBean apiBean = new ApiBean();
        apiBean.setContent(new ObjBean(productService.getProductInfo(id,httpSession)));
        apiBean.setRet(0);
        return apiBean;
    }
}
