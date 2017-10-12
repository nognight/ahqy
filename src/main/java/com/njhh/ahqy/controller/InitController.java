package com.njhh.ahqy.controller;

import com.njhh.ahqy.entity.User;
import com.njhh.ahqy.service.InitService;
import com.njhh.ahqy.service.UserService;
import com.njhh.ahqy.util.ValidateCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by HiWin10 on 2017/6/14.
 */
@RestController
public class InitController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InitService initService;





    @RequestMapping("/api/init")
    private String init() {
        logger.info("test");
//        return initService.getUser();
        return "api ok";
    }

    /**
     * 图形验证码
     * @return
     */
    @RequestMapping(value="/api/validateCode")
    public String validateCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        HttpSession session = request.getSession();
        String type = request.getParameter("type");
        String phoneNum = request.getParameter("phoneNum");
        ValidateCode vCode = initService.getValidateCode(type,phoneNum,session);
        vCode.write(response.getOutputStream());
        return null;
    }
}
