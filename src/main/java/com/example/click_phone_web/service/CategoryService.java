package com.example.click_phone_web.service;

import com.example.click_phone_web.entity.CategoryEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<CategoryEntity> findByCategoryAndDeleteFlagIsFalse();
}
