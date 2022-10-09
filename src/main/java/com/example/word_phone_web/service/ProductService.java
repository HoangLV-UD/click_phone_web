package com.example.word_phone_web.service;

import com.example.word_phone_web.dto.respone.product.NewProductRespone;
import com.example.word_phone_web.dto.respone.product.ProductRespone;
import com.example.word_phone_web.entity.ProductEntity;

import java.util.List;

public interface ProductService {

    List<NewProductRespone>  findAll();

    ProductRespone findById(Long id);

    List<NewProductRespone> findByCateId(Long id);

    List<NewProductRespone> findByName(String name);
}
