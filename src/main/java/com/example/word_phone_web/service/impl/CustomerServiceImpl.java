package com.example.word_phone_web.service.impl;

import com.example.word_phone_web.entity.CustomerEntity;
import com.example.word_phone_web.repo.CustomerRepo;
import com.example.word_phone_web.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author: hieu
 * @since: 19/07/2022
 * Project_name: com.example.word_phone_web.service.impl
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo repo;

    @Override
    public CustomerEntity findByEmail(String email) {
        return repo.findByEmail(email);
    }
}
