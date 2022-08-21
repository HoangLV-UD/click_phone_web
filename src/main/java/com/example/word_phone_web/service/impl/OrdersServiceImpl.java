package com.example.word_phone_web.service.impl;

import com.example.word_phone_web.config.Config;
import com.example.word_phone_web.dto.request.orders.OrdersRequest;
import com.example.word_phone_web.dto.respone.VPResponDto;
import com.example.word_phone_web.entity.*;
import com.example.word_phone_web.repo.*;
import com.example.word_phone_web.service.OrderService;
import com.example.word_phone_web.util.SessionUtil;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:
 *
 * @author: hieu
 * @since: 13/08/2022
 * Project_name: com.example.word_phone_web.service.impl
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersServiceImpl implements OrderService {
    private final OrdersRepo ordersRepo;
    private final OrdersDetailRepo ordersDetailRepo;
    private final CartRepo cartRepo;
    private final CustomerRepo customerRepo;
    private final SessionUtil sessionUtil;
    private final VoucherRepo voucherRepo;
    @Override
    public String createOrder(OrdersRequest request) {
        try {
                CustomerEntity customerEntity = customerRepo.findByEmail(String.valueOf(sessionUtil.getObject("username")));
                OrdersEntity ordersEntity = new OrdersEntity();
                if(request.getVoucherCode() != null){
                    VoucherEntity voucherEntity = voucherRepo.findByDeleteFlagIsFalseAndCode(request.getVoucherCode());
                    ordersEntity.setVoucherEntity(voucherEntity);
                }
                ordersEntity.setCustomerEntity(customerEntity);
                ordersEntity.setAddress(request.getAddress());
                ordersEntity.setStatusPay(0);
                ordersEntity.setNote(request.getNote());
                ordersEntity.setStatus("0");
                ordersEntity.setTotalMoney(request.getTotalMoney());
                OrdersEntity ordersEntity1 = ordersRepo.save(ordersEntity);
                ordersEntity1.setCodeOrder("HD000" + ordersEntity1.getId());
                ordersRepo.save(ordersEntity1);
                List<CartEntity> cartEntities = cartRepo.findByDeleteFlagIsFalseAndIdCustomer(customerEntity.getId());
                for (CartEntity cart: cartEntities
                     ) {
                    OrdersDetailEntity ordersDetailEntity = new OrdersDetailEntity();
                    ordersDetailEntity.setOrdersEntity(ordersEntity1);
                    ordersDetailEntity.setQuantity(cart.getQuantity());
                    ordersDetailEntity.setIdPropertyProduct(cart.getIdProduct().getId());
                    ordersDetailRepo.save(ordersDetailEntity);
                    cartRepo.delete(cart);
                }
            return "ok";
        }catch (Exception e){
            return "false";
        }


    }

    @Override
    public VPResponDto createOrderOnline(OrdersRequest request , HttpServletRequest requesthttp) {
        try {
            Gson gson = new Gson();
            CustomerEntity customerEntity = customerRepo.findByEmail(String.valueOf(sessionUtil.getObject("username")));
            OrdersEntity ordersEntity = new OrdersEntity();
            if(request.getVoucherCode() != null){
                VoucherEntity voucherEntity = voucherRepo.findByDeleteFlagIsFalseAndCode(request.getVoucherCode());
                ordersEntity.setVoucherEntity(voucherEntity);
            }

            ordersEntity.setCustomerEntity(customerEntity);
            ordersEntity.setAddress(request.getAddress());
            ordersEntity.setStatusPay(0);
            ordersEntity.setNote(request.getNote());
            ordersEntity.setStatus("0");
            ordersEntity.setTotalMoney(request.getTotalMoney());
            OrdersEntity ordersEntity1 = ordersRepo.save(ordersEntity);
            ordersEntity1.setCodeOrder("HD000" + ordersEntity1.getId());
            ordersRepo.save(ordersEntity1);
            List<CartEntity> cartEntities = cartRepo.findByDeleteFlagIsFalseAndIdCustomer(customerEntity.getId());
            for (CartEntity cart: cartEntities
            ) {
                OrdersDetailEntity ordersDetailEntity = new OrdersDetailEntity();
                ordersDetailEntity.setOrdersEntity(ordersEntity1);
                ordersDetailEntity.setQuantity(cart.getQuantity());
                ordersDetailEntity.setIdPropertyProduct(cart.getIdProduct().getId());
                ordersDetailRepo.save(ordersDetailEntity);
                cartRepo.delete(cart);
            }
            VPResponDto vpResponDto = gson.fromJson(VNPAYService.payments(ordersEntity1.getId(),10000000, Config.getRandomNumber(8), requesthttp), VPResponDto.class);

            return vpResponDto;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public String updateOrder(OrdersRequest request) {
        return null;
    }

    @Override
    public String deleteOrder(long id) {
        return null;
    }
}
