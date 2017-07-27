package com.njhh.ahqy.controller;

import com.njhh.ahqy.controller.restfulBeans.ApiBean;
import com.njhh.ahqy.controller.restfulBeans.CodeBean;
import com.njhh.ahqy.controller.restfulBeans.ObjBean;
import com.njhh.ahqy.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
        apiBean.setContent(new ObjBean(privilegeService.getPrivilegeList(type, category, httpSession)));
        return apiBean;

    }

    @RequestMapping("/api/privilege/getAdList")
    private ApiBean getAdList(HttpSession httpSession,
                              int type,
                              int source,
                              int id) {
        ApiBean apiBean = new ApiBean();
        apiBean.setRet(0);
        apiBean.setContent(new ObjBean(privilegeService.getPrivilegeAdList(type, source, id, httpSession)));
        return apiBean;
    }


    @RequestMapping("/api/privilege/getUserList")
    private ApiBean getUserList(HttpSession httpSession,
                                int type) {
        ApiBean apiBean = new ApiBean();
        apiBean.setRet(0);
        apiBean.setContent(new ObjBean(privilegeService.getUserPrivilegeList(httpSession)));
        return apiBean;

    }


    @RequestMapping("/api/privilege/getInfo")
    private ApiBean getPrivilegeList(HttpSession httpSession,
                                     int id) {
        ApiBean apiBean = new ApiBean();
        apiBean.setRet(0);
        apiBean.setContent(new ObjBean(privilegeService.getPrivilegeInfo(id, httpSession)));
        return apiBean;

    }

    @RequestMapping("/api/privilege/use")
    private ApiBean usePrivilege(HttpSession httpSession,
                                 int id,
                                 String authCode) {
        ApiBean apiBean = new ApiBean();
        apiBean.setRet(0);
        apiBean.setContent(new CodeBean(privilegeService.usePrivilege(id, authCode,0 ,httpSession)));
        return apiBean;

    }

    @RequestMapping("/api/privilege/useById")
    private ApiBean usePrivilegeById(HttpSession httpSession,
                                     String channel,
                                     int id,
                                     String phoneNum,
                                     String timestamp,
                                     String sign) {
        ApiBean apiBean = new ApiBean();
        apiBean.setRet(0);
        apiBean.setContent(new CodeBean(privilegeService.usePrivilegeById(channel,id, phoneNum,timestamp,sign, httpSession)));
        return apiBean;

    }


    @RequestMapping("/api/privilege/authCode")
    private ApiBean getAuthCode(HttpSession httpSession,
                                int id) {
        ApiBean apiBean = new ApiBean();
        apiBean.setRet(0);
        apiBean.setContent(new CodeBean(privilegeService.getAuthCode(id, httpSession)));
        return apiBean;
    }

}
