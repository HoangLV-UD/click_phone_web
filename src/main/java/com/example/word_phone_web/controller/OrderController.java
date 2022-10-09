package com.example.word_phone_web.controller;

import com.example.word_phone_web.dto.respone.cart.CartRespone;
import com.example.word_phone_web.entity.CustomerEntity;
import com.example.word_phone_web.entity.OrdersEntity;
import com.example.word_phone_web.repo.OrdersRepo;
import com.example.word_phone_web.service.CartService;
import com.example.word_phone_web.service.CustomerService;
import com.example.word_phone_web.util.ConvertUtil;
import com.example.word_phone_web.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

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
    private final OrdersRepo ordersRepo;
    @GetMapping("success")
    public String success(HttpServletRequest request){
        System.out.println(request.getContextPath());
        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL());
        if(request.getParameter("vnp_ResponseCode").equals("00")){
            String id = request.getParameter("vnp_OrderInfo");
            Optional<OrdersEntity> o = ordersRepo.findById(Long.valueOf(id));
            o.get().setStatus("0");
            ordersRepo.save(o.get());
        }
        return "redirect:/cart";
    }

    @GetMapping("")
    public String index(Model model){
        List<CartRespone> list = cartService.findByCustomer();

        CustomerEntity customerEntity = customerService.findByEmail(String.valueOf(sessionUtil.getObject("username")));

        long tong = 0;
        boolean check = true;
        for (CartRespone cart: list
        ) {
            tong+=cart.getTotal();
            if(cart.getPriceProductPromotion() > 0){
                check = false;
            }
        }
        model.addAttribute("promotion", check ? 1 : 0);
        model.addAttribute("listCart", list);
        model.addAttribute("tong", convertUtil.moneyToStringFormat(tong));
        model.addAttribute("tongOrder", convertUtil.moneyToStringFormat(tong + 30000));
        model.addAttribute("tongPrice", tong);
        model.addAttribute("cutomer", customerEntity);
        return "views/checkout/checkout";
    }
}
