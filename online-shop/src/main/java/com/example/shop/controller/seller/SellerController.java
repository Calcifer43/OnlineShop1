package com.example.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.shop.entity.AdminUser;//这两个需要待改
import com.example.shop.service.AdminUserService;//这两个需要待改

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private AdminUserService userService;
    /**
     * 访问首页
     *
     * @return
     */
    @RequestMapping("/toIndex")
    public String toIndex() {
        return "seller/index";
    }

    /**
     * 访问登录页面
     *
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "seller/login";
    }

    /**
     * 登录请求
     *
     * @param username
     * @param password
     */
    //@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/login.do")
    public void login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        AdminUser seller =userService.checkLogin(request, username, password);
        request.getSession().setAttribute("login_user",seller);
        response.sendRedirect("/seller/toIndex");
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/logout.do")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("login_user");
        response.sendRedirect("/seller/toLogin");
    }
}

