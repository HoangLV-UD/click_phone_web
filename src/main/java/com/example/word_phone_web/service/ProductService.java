package com.example.word_phone_web.service;

import com.example.word_phone_web.dto.respone.product.ProductRespone;

public interface ProductService {

    ProductRespone findById(Long id);
}
