package com.example.shop.controller.user;

import com.example.shop.entity.User;
import com.example.shop.entity.pojo.ResultBean;
import com.example.shop.service.UserService;
import com.example.shop.service.exception.LoginException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("mall/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 打开注册页面
     *
     * @return
     */
    @RequestMapping("/toRegister.html")
    public String toRegister() {
        return "mall/user/register";
    }

    @RequestMapping("/toUserHome.html")
    public String toFirst() {
        return "mall/user/userHome";
    }


    /**
     * 打开登录页面
     *
     * @return
     */
    @RequestMapping("/toLogin.html")//,method= RequestMethod.GET)
    //@RequestMapping("/toLogin.html")
    public String toLogin() {
        return "mall/user/login";
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     */
    @RequestMapping("/login.do")
    public String login(@RequestParam("username") String username,
                      @RequestParam("password") String password,
                      HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        System.out.println(username+" "+password);
        User user = userService.checkLogin(username, password);
        if (user != null) {
            //登录成功 重定向到不同的页面
            request.getSession().setAttribute("user", user);
            if(user.getFlag().equals("1")||user.getFlag().equals("2")){
                return "/mall/index.html";
            }else if(user.getFlag().equals("3")){
                return "/seller/index";
            }
            response.sendRedirect("/mall/index.html");
        } else {
            throw new LoginException("登录失败！ 用户名或者密码错误");
        }

        return "mall/error.html";
    }



    /**
     * 注册
     */
    @RequestMapping("/register.do")
    public String register(String username,
                         String password,
                         String name,
                         String phone,
                         String email,
                         String addr,String flag,
                         HttpServletResponse response) throws IOException {
        System.out.println(flag);
        User user = new User();
        user.setUsername(username);
        user.setPhone(phone);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setAddr(addr);
        user.setFlag(flag);
        userService.create(user);
        // 注册完成后重定向到登录页面
        return "/mall/user/login";
        //response.sendRedirect("/mall/user/login.html");
    }

    /**
     * 登出
     */
    @RequestMapping("/logout.do")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
        return "mall/user/login";
        //response.sendRedirect("mall/index.html");
    }

    /**
     * 验证用户名是否唯一
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkUsername.do")
    public ResultBean<Boolean> checkUsername(String username) throws JsonProcessingException {
        List<User> users = userService.findByUsername(username);
        if (users==null||users.isEmpty()){
            return new ResultBean<>(true);
        }
        return new ResultBean<>(false);
    }

    /**
     * 如发生错误 转发到这页面
     *
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/error.html")
    public String error(HttpServletResponse response, HttpServletRequest request) {
        return "error";
    }
}
