package com.example.word_phone_web.service.impl;

import com.example.word_phone_web.dto.respone.cart.CartRespone;
import com.example.word_phone_web.entity.CartEntity;
import com.example.word_phone_web.entity.ProductEntity;
import com.example.word_phone_web.entity.ProductPropertyEntity;
import com.example.word_phone_web.entity.RomEntity;
import com.example.word_phone_web.repo.CartRepo;
import com.example.word_phone_web.repo.ProductRepo;
import com.example.word_phone_web.repo.RomRepo;
import com.example.word_phone_web.service.CartService;
import com.example.word_phone_web.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author: hieu
 * @since: 18/07/2022
 * Project_name: com.example.word_phone_web.service.impl
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceimpl implements CartService {
    private final CartRepo cartRepo;
    private final ConvertUtil convertUtil;
    private final ColorServiceImpl colorService;
    private final RomRepo romRepo;
    private final ProductRepo productRepo;

    @Override
    public List<CartRespone> findByCustomer(Long id) {
        List<CartEntity> entities = cartRepo.findByDeleteFlagIsFalseAndIdCustomer(id);
        return entities.stream().map(this::mapToRespone).collect(Collectors.toList());
    }

    private CartRespone mapToRespone(CartEntity entity){
        CartRespone respone = new CartRespone();
        respone.setId(String.valueOf(entity.getId()));
        respone.setColor(colorService.findById(entity.getIdProduct().getColorId()));
        ProductPropertyEntity propertyEntity = entity.getIdProduct();
        RomEntity romEntity = romRepo.findById(propertyEntity.getRomId()).get();
        ProductEntity productEntity = productRepo.findById(romEntity.getProductId()).get();
        respone.setImgProduct(productEntity.getImage_key());
        respone.setIdProduct(String.valueOf(productEntity.getId()));
        respone.setNameProduct(productEntity.getName());
        respone.setQuantity(entity.getQuantity());
        respone.setPriceProduct(propertyEntity.getPrice());
        respone.setPriceProductString(convertUtil.moneyToStringFormat(respone.getPriceProduct()));
        respone.setTotal(respone.getQuantity() * respone.getPriceProduct());
        respone.setTotalString(convertUtil.moneyToStringFormat(respone.getTotal()));
        respone.setRom(romEntity.getName() + " GB");
        return respone;
    }
}
