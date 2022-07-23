package com.example.word_phone_web.controller;

import com.example.word_phone_web.dto.respone.cart.CartRespone;
import com.example.word_phone_web.entity.CustomerEntity;
import com.example.word_phone_web.service.CartService;
import com.example.word_phone_web.service.CustomerService;
import com.example.word_phone_web.util.ConvertUtil;
import com.example.word_phone_web.util.SessionUtil;
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
 * @since: 24/07/2022
 * Project_name: com.example.word_phone_web.controller
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final CartService cartService;

    private final ConvertUtil convertUtil;

    private final SessionUtil sessionUtil;

    private final CustomerService customerService;

    @GetMapping("")
    public String index(Model model){
        List<CartRespone> list = cartService.findByCustomer();

        CustomerEntity customerEntity = customerService.findByEmail(String.valueOf(sessionUtil.getObject("username")));

        long tong = 0;
        for (CartRespone cart: list
        ) {
            tong+=cart.getTotal();
        }
        model.addAttribute("listCart", list);
        model.addAttribute("tong", convertUtil.moneyToStringFormat(tong));
        model.addAttribute("cutomer", customerEntity);
        return "views/checkout/checkout";
    }
}
