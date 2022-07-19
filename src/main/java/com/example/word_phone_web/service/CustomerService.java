package com.example.word_phone_web.service;

import com.example.word_phone_web.entity.CustomerEntity;

/**
 * Description:
 *
 * @author: hieu
 * @since: 19/07/2022
 * Project_name: com.example.word_phone_web.service
 */
public interface CustomerService {
    CustomerEntity findByEmail(String email);
}
