package com.example.click_phone_web.service;

import com.example.click_phone_web.dto.respone.product.ProductPropertyRespone;

import java.util.List;

public interface ProductPropertyService {

    List<ProductPropertyRespone> findByRomId(Long romId);
}
