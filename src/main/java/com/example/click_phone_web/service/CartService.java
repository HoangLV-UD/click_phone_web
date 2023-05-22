package com.example.click_phone_web.service;

import com.example.click_phone_web.dto.request.cart.CartEditRequest;
import com.example.click_phone_web.dto.request.cart.CartRequest;
import com.example.click_phone_web.dto.respone.cart.CartRespone;

import java.util.List;

public interface CartService {
    List<CartRespone> findByCustomer();
    String addCart(CartRequest cartRequest);

    String deleteCart(Long id);

    String updateCart(List<CartEditRequest> list);
}
