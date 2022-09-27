package com.example.word_phone_web.service.impl;

import com.example.word_phone_web.config.Config;
import com.example.word_phone_web.dto.request.orders.OrdersRequest;
import com.example.word_phone_web.dto.respone.VPResponDto;
import com.example.word_phone_web.dto.respone.cart.CartRespone;
import com.example.word_phone_web.dto.respone.order.OrderRespone;
import com.example.word_phone_web.entity.*;
import com.example.word_phone_web.repo.*;
import com.example.word_phone_web.service.OrderService;
import com.example.word_phone_web.util.ConvertUtil;
import com.example.word_phone_web.util.SessionUtil;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

    private final ConvertUtil util;
    private final ColorServiceImpl colorService;
    private final RomRepo romRepo;

    private final ProductRepo productRepo;

    private final ProductPropertyRepo productPropertyRepo;
    @Override
    public String createOrder(OrdersRequest request) {
        try {
                CustomerEntity customerEntity = customerRepo.findByEmail(String.valueOf(sessionUtil.getObject("username")));
                OrdersEntity ordersEntity = new OrdersEntity();
                if(request.getVoucherCode() != null){
                    long millis=System.currentTimeMillis();
                    java.sql.Date date=new java.sql.Date(millis);
                    VoucherEntity voucherEntity = voucherRepo.findByDeleteFlagIsFalseAndCode(date,request.getVoucherCode());
                    ordersEntity.setVoucherEntity(voucherEntity);
                }
                ordersEntity.setCustomerEntity(customerEntity);
                ordersEntity.setAddress(request.getAddress());
                ordersEntity.setStatusPay(0);
                ordersEntity.setNote(request.getNote());
                ordersEntity.setStatus("0");
                ordersEntity.setTypeOrder(1);
                ordersEntity.setTotalMoney(request.getTotalMoney());
                OrdersEntity ordersEntity1 = ordersRepo.save(ordersEntity);
                ordersEntity1.setCodeOrder("HD000" + ordersEntity1.getId());
                ordersRepo.save(ordersEntity1);
                List<CartEntity> cartEntities = cartRepo.findByDeleteFlagIsFalseAndIdCustomer(customerEntity.getId());
                for (CartEntity cart: cartEntities
                     ) {
                    OrdersDetailEntity ordersDetailEntity = new OrdersDetailEntity();
                    ProductPropertyEntity propertyEntity = productPropertyRepo.findByDeleteFlagIsFalseAndId(cart.getIdProduct().getId());
                    propertyEntity.setQuantity(propertyEntity.getQuantity() - cart.getQuantity());

                    ordersDetailEntity.setOrdersEntity(ordersEntity1);
                    ordersDetailEntity.setQuantity(cart.getQuantity());
                    ordersDetailEntity.setIdPropertyProduct(cart.getIdProduct().getId());
                    ordersDetailEntity.setPrice(cart.getIdProduct().getPricePromotion() == 0 ? cart.getIdProduct().getPrice()
                            : cart.getIdProduct().getPricePromotion());
                    ordersDetailRepo.save(ordersDetailEntity);
                    cartRepo.delete(cart);
                    productPropertyRepo.save(propertyEntity);
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
                long millis=System.currentTimeMillis();
                java.sql.Date date=new java.sql.Date(millis);
                VoucherEntity voucherEntity = voucherRepo.findByDeleteFlagIsFalseAndCode(date,request.getVoucherCode());
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

    @Override
    public List<OrderRespone> findAllOrder() {
        CustomerEntity customerEntity = customerRepo.findByEmail(String.valueOf(sessionUtil.getObject("username")));
        List<OrdersEntity> entityList = ordersRepo.findByCustomerEntityOrderByCreateDateDesc(customerEntity);
        List<OrderRespone> list = new ArrayList<>();
        for (OrdersEntity entity: entityList
             ) {
                OrderRespone respone = new OrderRespone();
                respone.setId(String.valueOf(entity.getId()));
                respone.setCreateDate(String.valueOf(entity.getCreateDate()));
                respone.setStatus(entity.getStatus());
                respone.setCodeOrder(entity.getCodeOrder());
                respone.setTotalString(util.moneyToStringFormat(entity.getTotalMoney()));
                list.add(respone);
        }
        return list;
    }

    @Override
    public List<CartRespone> findByOrderDetail(String id) {
        OrdersEntity entity = ordersRepo.findByCodeOrder(id);
        List<OrdersDetailEntity> list = ordersDetailRepo.findByDeleteFlagIsFalseAndOrdersEntity(entity.getId());
        List<CartRespone> list1 = new ArrayList<>();
        for (OrdersDetailEntity detail: list
             ) {
            CartRespone respone = mapToRespone(detail);
            list1.add(respone);
        }
        return list1;
    }

    @Override
    public OrdersEntity findByOrder(String id) {
        OrdersEntity entity = ordersRepo.findByCodeOrder(id);
        return entity;
    }

    @Override
    public String canncelOrder(String id) {
        OrdersEntity entity = ordersRepo.findByCodeOrder(id);
        entity.setStatus("-1");
        ordersRepo.save(entity);
        return "ok";
    }

    @Override
    public String datLai(String id) {
        OrdersEntity entity = ordersRepo.findByCodeOrder(id);
        entity.setStatus("0");
        ordersRepo.save(entity);
        return "ok";
    }

    private CartRespone mapToRespone(OrdersDetailEntity entity){
        CartRespone respone = new CartRespone();
        ProductPropertyEntity propertyEntity = productPropertyRepo.findByDeleteFlagIsFalseAndId(entity.getIdPropertyProduct());
        respone.setId(String.valueOf(entity.getId()));
        respone.setColor(colorService.findById(propertyEntity.getColorId()));
        RomEntity romEntity = romRepo.findById(propertyEntity.getRomId()).get();
        ProductEntity productEntity = productRepo.findById(romEntity.getProductId()).get();
        respone.setImgProduct(productEntity.getImage_key());
        respone.setIdProduct(String.valueOf(productEntity.getId()));
        respone.setQuantityProduct(propertyEntity.getQuantity());
        respone.setNameProduct(productEntity.getName());
        respone.setQuantity(entity.getQuantity());
        respone.setPriceProduct(entity.getPrice());
        respone.setPriceProductString(util.moneyToStringFormat(entity.getPrice()));
        respone.setRom(romEntity.getName() + " GB");
        respone.setPriceProductPromotion(0);
        respone.setPriceProductPromotionString(util.moneyToStringFormat(0L));
        if(respone.getPriceProductPromotion() > 0){
            respone.setTotal(respone.getQuantity() * respone.getPriceProductPromotion());
            respone.setTotalString(util.moneyToStringFormat(respone.getTotal()));
        }else {
            respone.setTotal(respone.getQuantity() * respone.getPriceProduct());
            respone.setTotalString(util.moneyToStringFormat(respone.getTotal()));
        }
        return respone;
    }

}
