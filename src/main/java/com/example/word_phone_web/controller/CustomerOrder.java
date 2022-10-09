package com.example.word_phone_web.controller;

import com.example.word_phone_web.dto.respone.cart.CartRespone;
import com.example.word_phone_web.dto.respone.order.OrderRespone;
import com.example.word_phone_web.entity.OrdersEntity;
import com.example.word_phone_web.entity.VoucherEntity;
import com.example.word_phone_web.repo.VoucherRepo;
import com.example.word_phone_web.service.OrderService;
import com.example.word_phone_web.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 19/09/2022
 * Project_name: com.example.word_phone_web.controller
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("your-orders")
public class CustomerOrder {
    private final OrderService service;

    private final ConvertUtil convertUtil;

    private final VoucherRepo voucherRepo;

    @GetMapping("orders")
    public String index(Model model){
        List<OrderRespone> list = service.findAllOrder();
        model.addAttribute("listCart", list);
        return "/views/orders/order";
    }

    @GetMapping("orders/{id}")
    public String index(@PathVariable String id, Model model){
        List<CartRespone> list = service.findByOrderDetail(id);
        OrdersEntity entity = service.findByOrder(id);
        VoucherEntity voucherEntity = entity.getVoucherEntity();
        long khuyenMai = 0;
        long tong = 0;
        for (CartRespone cart: list
        ) {
            tong+=cart.getTotal();
        }
        model.addAttribute("tong1", convertUtil.moneyToStringFormat(tong));
        if(voucherEntity != null){
            if(voucherEntity.getTypeDiscount().equals("đ")){
                khuyenMai = voucherEntity.getDiscount();
                tong -= khuyenMai;
            }else {
                khuyenMai = tong / 100 * voucherEntity.getDiscount();
                tong -= khuyenMai;
            }
        }
        model.addAttribute("id", id);
        model.addAttribute("tong", convertUtil.moneyToStringFormat(tong));
        model.addAttribute("voucher", convertUtil.moneyToStringFormat(khuyenMai));
        model.addAttribute("check", entity.getStatus());
        model.addAttribute("listCart", list);
        return "/views/orders/order-detail";
    }
}
