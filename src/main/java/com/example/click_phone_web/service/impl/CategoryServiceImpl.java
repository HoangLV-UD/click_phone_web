package com.example.click_phone_web.service.impl;

import com.example.click_phone_web.entity.CategoryEntity;
import com.example.click_phone_web.repo.CategoryRepo;
import com.example.click_phone_web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<CategoryEntity> findByCategoryAndDeleteFlagIsFalse() {
        return categoryRepo.findByDeleteFlagIsFalse();
    }
}
