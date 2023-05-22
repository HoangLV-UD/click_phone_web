package com.example.click_phone_web.service;

import com.example.click_phone_web.dto.request.customer.CustomerRequest;
import com.example.click_phone_web.entity.CustomerEntity;

import java.text.ParseException;

public interface CustomerService {
    CustomerEntity findByEmail(String email);

    String addCustomer(CustomerRequest request) throws ParseException;

    CustomerEntity findByPhone(String phone);
}
