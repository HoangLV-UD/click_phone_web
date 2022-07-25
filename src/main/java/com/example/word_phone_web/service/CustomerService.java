package com.example.word_phone_web.service;

import com.example.word_phone_web.dto.request.customer.CustomerRequest;
import com.example.word_phone_web.entity.CustomerEntity;

import java.text.ParseException;

/**
 * Description:
 *
 * @author: hieu
 * @since: 19/07/2022
 * Project_name: com.example.word_phone_web.service
 */
public interface CustomerService {
    CustomerEntity findByEmail(String email);

    String addCustomer(CustomerRequest request) throws ParseException;

    CustomerEntity findByPhone(String phone);
}
