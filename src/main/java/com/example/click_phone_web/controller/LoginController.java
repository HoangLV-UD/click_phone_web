package com.example.click_phone_web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    @GetMapping("")
    public String index(Model model){
        return "views/login/login-register";
    }
//    @GetMapping("")
//    public String index2(){
//        return "views/login/login-register";
//    }
}
