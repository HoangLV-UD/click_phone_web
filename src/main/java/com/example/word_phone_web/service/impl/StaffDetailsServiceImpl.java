package com.example.word_phone_web.service.impl;


import com.example.word_phone_web.entity.CustomerEntity;
import com.example.word_phone_web.util.CookieUtil;
import com.example.word_phone_web.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StaffDetailsServiceImpl implements UserDetailsService {
    private final CustomerServiceImpl service;

    @Autowired
    private final HttpSession session;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomerEntity staffEntity = service.findByEmail(email);
        if(staffEntity == null){
            log.error("User not found! " + email);
            session.setAttribute("emailLogin", email);
            throw new UsernameNotFoundException("User " + email + " was not found in the database");
        }
        log.info("login success");
        // Add info logged user to Session
        session.setAttribute("user", staffEntity);
        List<GrantedAuthority> grantList = new ArrayList<>();
        return new User(staffEntity.getEmail(), staffEntity.getPassWord(), grantList);
    }
}
