package com.njhh.ahqy.controller;

import com.njhh.ahqy.controller.restfulBeans.ApiBean;
import com.njhh.ahqy.controller.restfulBeans.CodeBean;
import com.njhh.ahqy.controller.restfulBeans.Content;
import com.njhh.ahqy.controller.restfulBeans.ObjBean;
import com.njhh.ahqy.entity.Privilege;
import com.njhh.ahqy.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by HiWin10 on 2017/6/28.
 */
@RestController
public class PrivilegeController {
    @Autowired
    private PrivilegeService privilegeService;

    @RequestMapping("/api/privilege/getList")
    private ApiBean getPrivilegeList(HttpSession httpSession,
                                     int type,
                                     int category) {
        ApiBean apiBean = new ApiBean();
        apiBean.setRet(0);
        apiBean.setContent(new ObjBean(privilegeService.getPrivilegeList(type,category,httpSession)));
        return apiBean;

    }
    @RequestMapping("/api/privilege/getInfo")
    private ApiBean getPrivilegeList(HttpSession httpSession,
                                     int id) {
        ApiBean apiBean = new ApiBean();
        apiBean.setRet(0);
        apiBean.setContent(new ObjBean(privilegeService.getPrivilegeInfo(id,httpSession)));
        return apiBean;

    }

    @RequestMapping("/api/privilege/use")
    private ApiBean usePrivilege(HttpSession httpSession,
                                 int id,
                                 String authCode) {
        ApiBean apiBean = new ApiBean();
        apiBean.setRet(0);
        apiBean.setContent(new CodeBean(privilegeService.usePrivilege(id,authCode,httpSession)));
        return apiBean;

    }

    @RequestMapping("/api/privilege/authCode")
    private ApiBean getAuthCode(HttpSession httpSession,
                                int id){
        ApiBean apiBean = new ApiBean();
        apiBean.setRet(0);
        apiBean.setContent(new CodeBean(privilegeService.getAuthCode(id,httpSession)));
        return apiBean;
    }

}
