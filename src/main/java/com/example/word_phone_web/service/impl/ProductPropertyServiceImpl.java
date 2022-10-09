package com.example.word_phone_web.service.impl;

import com.example.word_phone_web.dto.respone.product.ProductPropertyRespone;
import com.example.word_phone_web.entity.ProductPropertyEntity;
import com.example.word_phone_web.repo.ProductPropertyRepo;
import com.example.word_phone_web.service.ColorService;
import com.example.word_phone_web.service.ProductPropertyService;
import com.example.word_phone_web.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductPropertyServiceImpl implements ProductPropertyService {

    private final ProductPropertyRepo repo;
    private final ConvertUtil util;

    private final ColorService colorService;

    @Override
    public List<ProductPropertyRespone> findByRomId(Long romId) {
        List<ProductPropertyEntity> entityList = repo.findByRomId(romId);
        List<ProductPropertyRespone> responeList = new ArrayList<>();
        entityList.forEach(o -> {
            responeList.add(ProductPropertyRespone.builder()
                    .price(o.getPrice())
                    .priceNow(util.moneyToStringFormat(o.getPricePromotion()*o.getPrice()/100))
                    .priceString(util.moneyToStringFormat(o.getPrice()))
                    .quantity(o.getQuantity())
                    .id(String.valueOf(o.getId()))
                    .color(colorService.findById(o.getColorId()))
                    .build());
        });
        return responeList;
    }
}
