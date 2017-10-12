package com.njhh.ahqy.controller;

import com.njhh.ahqy.controller.restfulBeans.ApiBean;
import com.njhh.ahqy.controller.restfulBeans.CodeBean;
import com.njhh.ahqy.controller.restfulBeans.ObjBean;
import com.njhh.ahqy.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
                                     @RequestParam(defaultValue = "0") int type,
                                     @RequestParam(defaultValue = "0") int category,
                                     @RequestParam(defaultValue = "0") int giftType) {
        ApiBean apiBean = new ApiBean();
        apiBean.setRet(0);
        apiBean.setContent(new ObjBean(privilegeService.getPrivilegeList(type, category,giftType ,httpSession)));
        return apiBean;

    }

    @RequestMapping("/api/privilege/getAdList")
    private ApiBean getAdList(HttpSession httpSession,
                              @RequestParam(defaultValue = "0") int type,
                              @RequestParam(defaultValue = "0") int source,
                              @RequestParam(defaultValue = "0") int id) {
        ApiBean apiBean = new ApiBean();
        apiBean.setRet(0);
        apiBean.setContent(new ObjBean(privilegeService.getPrivilegeAdList(type, source, id, httpSession)));
        return apiBean;
    }


    @RequestMapping("/api/privilege/getUserList")
    private ApiBean getUserList(HttpSession httpSession,
                                @RequestParam(defaultValue = "0") int type) {
        ApiBean apiBean = new ApiBean();
        apiBean.setRet(0);
        apiBean.setContent(new ObjBean(privilegeService.getUserPrivilegeList(httpSession, type)));
        return apiBean;

    }


    @RequestMapping("/api/privilege/getInfo")
    private ApiBean getPrivilegeInfo(HttpSession httpSession,
                                     @RequestParam(defaultValue = "0") int id) {
        ApiBean apiBean = new ApiBean();
        apiBean.setRet(0);
        apiBean.setContent(new ObjBean(privilegeService.getPrivilegeInfo(id, httpSession)));
        return apiBean;

    }

    @RequestMapping("/api/privilege/getName")
    private ApiBean getPrivilegeName(HttpSession httpSession,
                                     @RequestParam(defaultValue = "0") int id) {
        ApiBean apiBean = new ApiBean();
        apiBean.setRet(0);
        apiBean.setContent(new ObjBean(privilegeService.getPrivilegeName(id, httpSession)));
        return apiBean;

    }

    @RequestMapping("/api/privilege/use")
    private ApiBean usePrivilege(HttpSession httpSession,
                                 @RequestParam(defaultValue = "0") int id,
                                 String authCode) {
        ApiBean apiBean = new ApiBean();
        apiBean.setRet(0);
        apiBean.setContent(new CodeBean(privilegeService.usePrivilege(id, authCode, 0, httpSession)));
        return apiBean;

    }


    @RequestMapping("/api/privilege/addUserPrivilege")
    private ApiBean addUserPrivilege(HttpSession httpSession,
                                     @RequestParam(defaultValue = "0") int id,
                                     String authCode) {
        ApiBean apiBean = new ApiBean();
        apiBean.setRet(0);
        apiBean.setContent(new CodeBean(privilegeService.addUserPrivilege(id, 0, httpSession)));
        return apiBean;

    }

    @RequestMapping("/api/privilege/useById")
    private ApiBean usePrivilegeById(HttpSession httpSession,
                                     @RequestParam(defaultValue = "0") String channel,
                                     @RequestParam(defaultValue = "0") int id,
                                     @RequestParam(defaultValue = "0") String phoneNum,
                                     @RequestParam(defaultValue = "0") String timestamp,
                                     @RequestParam(defaultValue = "0") String sign) {
        ApiBean apiBean = new ApiBean();
        apiBean.setRet(0);
        apiBean.setContent(new CodeBean(privilegeService.usePrivilegeById(channel, id, phoneNum, timestamp, sign, httpSession)));
        return apiBean;

    }


    @RequestMapping("/api/privilege/authCode")
    private ApiBean getAuthCode(HttpSession httpSession,
                                @RequestParam(defaultValue = "0") int id) {
        ApiBean apiBean = new ApiBean();
        apiBean.setRet(0);
        apiBean.setContent(new CodeBean(privilegeService.getAuthCode(id, httpSession)));
        return apiBean;
    }

}
