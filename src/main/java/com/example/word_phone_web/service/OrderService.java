package com.example.word_phone_web.service;

import com.example.word_phone_web.dto.request.orders.OrdersRequest;
import com.example.word_phone_web.dto.respone.VPResponDto;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 *
 * @author: hieu
 * @since: 13/08/2022
 * Project_name: com.example.word_phone_web.service
 */
public interface OrderService {

    String createOrder(OrdersRequest request);

    VPResponDto createOrderOnline (OrdersRequest request , HttpServletRequest requesthttp);

    String updateOrder(OrdersRequest request);

    String deleteOrder(long id);


}
