package com.example.word_phone_web.service.impl;

import com.example.word_phone_web.dto.request.customer.CustomerRequest;
import com.example.word_phone_web.entity.CustomerEntity;
import com.example.word_phone_web.repo.CustomerRepo;
import com.example.word_phone_web.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public CustomerEntity findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public String addCustomer(CustomerRequest request) throws ParseException {
        try {
            CustomerEntity entity = new CustomerEntity();
            entity.setEmail(request.getEmail());
            entity.setFullName(request.getName());
            entity.setPhoneNumber(request.getPhone());

            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(request.getDate());
            entity.setDateOfBirth(date1);
            entity.setPassWord(bCryptPasswordEncoder.encode(request.getPassword()));
            repo.save(entity);
        }catch (Exception e){
            return "false";
        }
        return "ok";
    }

    @Override
    public CustomerEntity findByPhone(String phone) {
        return repo.findByPhoneNumber(phone);
    }
}
