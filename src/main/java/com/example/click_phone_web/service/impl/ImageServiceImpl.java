package com.example.click_phone_web.service.impl;

import com.example.click_phone_web.entity.ImageEntity;
import com.example.click_phone_web.repo.ImageRepo;
import com.example.click_phone_web.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepo repo;
    @Override
    public List<String> getAllImageByProduct(String productId) {
        List<String> stringList = new ArrayList<>();
        List<ImageEntity> entityList = repo.findByProductIdAndDeleteFlagIsFalse(Long.valueOf(productId));
        entityList.forEach(o -> {
            stringList.add(o.getLing_image());
        });
        return stringList;
    }
}
