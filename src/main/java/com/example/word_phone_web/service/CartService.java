package com.example.word_phone_web.service;

import com.example.word_phone_web.dto.request.cart.CartEditRequest;
import com.example.word_phone_web.dto.request.cart.CartRequest;
import com.example.word_phone_web.dto.respone.cart.CartRespone;

import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 18/07/2022
 * Project_name: com.example.word_phone_web.service
 */
public interface CartService {
    List<CartRespone> findByCustomer();
    String addCart(CartRequest cartRequest);

    String deleteCart(Long id);

    String updateCart(List<CartEditRequest> list);
}
