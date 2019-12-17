package com.example.shop.controller;

import com.example.shop.entity.User;
import com.example.shop.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller

public class hand {
    @Autowired
    private UserServiceImpl service;

    @RequestMapping(value="/xihuan",method= RequestMethod.GET)
   //@GetMapping(value = "/hello")
    public String hello(Model model) {
        String name = "dingcong";
        model.addAttribute("name", name);
        return "index";
    }
    @RequestMapping(value="/hand",method= RequestMethod.GET)
        public List<User> queryBook(String username){
            return service.findByUsername(username);
        }


}
