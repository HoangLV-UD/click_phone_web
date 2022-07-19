package com.example.word_phone_web.controller;

import com.example.word_phone_web.dto.respone.cart.CartRespone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 19/07/2022
 * Project_name: com.example.word_phone_web.controller
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    @GetMapping("")
    public String index(Model model){
        return "views/login/login-register";
    }
}
