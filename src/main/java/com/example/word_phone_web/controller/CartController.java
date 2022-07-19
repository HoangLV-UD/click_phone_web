package com.example.word_phone_web.controller;

import com.example.word_phone_web.dto.respone.cart.CartRespone;
import com.example.word_phone_web.service.CartService;
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
 * @since: 18/07/2022
 * Project_name: com.example.word_phone_web.controller
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("")
    public String index(Model model){
        List<CartRespone> list = cartService.findByCustomer(1L);
        model.addAttribute("listCart", list);
        return "views/cart";
    }
}
