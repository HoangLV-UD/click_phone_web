package com.example.click_phone_web.service;

import com.example.click_phone_web.dto.respone.attribute.AttributeRespone;

public interface AttributeService {
    AttributeRespone findByProduct(Long id);
}
