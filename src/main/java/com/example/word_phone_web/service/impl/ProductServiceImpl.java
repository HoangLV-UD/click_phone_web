package com.example.word_phone_web.service.impl;

import com.example.word_phone_web.dto.respone.product.ProductRespone;
import com.example.word_phone_web.dto.respone.rom.RomRespone;
import com.example.word_phone_web.entity.ProductEntity;
import com.example.word_phone_web.repo.ProductRepo;
import com.example.word_phone_web.service.ImageService;
import com.example.word_phone_web.service.ProductService;
import com.example.word_phone_web.service.RomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    private final ImageServiceImpl imageService;

    private final RomServiceImpl romService;

    private final AttributeServiceImpl attributeService;



    @Override
    public ProductRespone findById(Long id) {
        Optional<ProductEntity> productEntity = productRepo.findByIdProduct(id);
        if(productEntity.isPresent()){
            ProductRespone respone = mapToEntity(productEntity.get());
            respone.setSrcImage(imageService.getAllImageByProduct(respone.getId())); // set list image cho product
            respone.setRomRespones(romService.findByProductId(Long.valueOf(respone.getId()))); // set list Rom cho product
            respone.setAttributeRespone(attributeService.findByProduct(productEntity.get().getId()));

            for (int i = 0; i < respone.getRomRespones().size(); i++) {
                if(respone.getRomRespones().get(i).getProductPropertyRespones().size() == 0){
                    respone.getRomRespones().remove(i);
                }
            }
            return respone;
        }
        log.error("Không tìm thấy product");
        return null;
    }

    private ProductRespone mapToEntity(ProductEntity entity){
        ProductRespone respone = new ProductRespone();
        respone.setId(String.valueOf(entity.getId()));
        respone.setName(entity.getName());
        respone.setNote(entity.getNote());
        respone.setImageKey(entity.getImage_key());
        return respone;
    }
}
