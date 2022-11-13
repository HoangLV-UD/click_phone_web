package com.example.word_phone_web.service.impl;

import com.example.word_phone_web.dto.respone.product.NewProductRespone;
import com.example.word_phone_web.dto.respone.product.ProductRespone;
import com.example.word_phone_web.dto.respone.rom.RomRespone;
import com.example.word_phone_web.entity.CategoryEntity;
import com.example.word_phone_web.entity.ProductEntity;
import com.example.word_phone_web.entity.ProductPropertyEntity;
import com.example.word_phone_web.entity.RomEntity;
import com.example.word_phone_web.repo.CategoryRepo;
import com.example.word_phone_web.repo.ProductPropertyRepo;
import com.example.word_phone_web.repo.ProductRepo;
import com.example.word_phone_web.service.ImageService;
import com.example.word_phone_web.service.ProductService;
import com.example.word_phone_web.service.RomService;
import com.example.word_phone_web.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ConvertUtil convertUtil;
    private final ProductRepo productRepo;

    private final CategoryRepo categoryRepo;

    private final ImageServiceImpl imageService;

    private final RomServiceImpl romService;

    private final AttributeServiceImpl attributeService;

    private final ProductPropertyRepo productPropertyRepo;

    @Override
    public List<NewProductRespone>  findAll() {
        List<NewProductRespone> listPro = new ArrayList<>();
        List<ProductEntity> list = productRepo.findAll();
        for (ProductEntity product : list) {
            boolean check = true;
            NewProductRespone productRespone = new NewProductRespone();
            productRespone.setId(String.valueOf(product.getId()));
            productRespone.setName(product.getName());
            productRespone.setNote(product.getNote());
            productRespone.setImageKey(product.getImage_key());
            productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
            productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
            productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
            int size = productRespone.getRomRespones().size();
            for (int i = 0; i < size; i++) {
                List<ProductPropertyEntity> productPropertyEntities = productPropertyRepo.findByRomId(Long.parseLong(productRespone.getRomRespones().get(i).getId()));
                if(productPropertyEntities == null || productPropertyEntities.size() == 0){
                    check = false;
                    productRespone.getRomRespones().remove(i);
                    size--;
                    continue;
                }else {
                    for (ProductPropertyEntity listProductProperty : productPropertyEntities) {
                        productRespone.setPrice(convertUtil.moneyToStringFormat(listProductProperty.getPrice()));
                        productRespone.setPricePromotion(listProductProperty.getPricePromotion());
                    }
                }
            }
            if(check){
                listPro.add(productRespone);
            }
        }

        return listPro;
    }

    @Override
    public ProductRespone findById(Long id) {
        Optional<ProductEntity> productEntity = productRepo.findByIdProduct(id);
        if (productEntity.isPresent()) {
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

    @Override
    public List<NewProductRespone> findByCateId(Long id) {
        List<NewProductRespone> listPro = new ArrayList<>();
        Optional<CategoryEntity> categoryEntity = categoryRepo.findById(id);
        List<ProductEntity> list = productRepo.findByCategoryId(categoryEntity.get().getId());
        for (ProductEntity product : list) {
            boolean check = true;
            NewProductRespone productRespone = new NewProductRespone();
            productRespone.setId(String.valueOf(product.getId()));
            productRespone.setName(product.getName());
            productRespone.setNote(product.getNote());
            productRespone.setImageKey(product.getImage_key());
            productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
            productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
            productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
            int size = productRespone.getRomRespones().size();
            for (int i = 0; i < size; i++) {
                List<ProductPropertyEntity> productPropertyEntities = productPropertyRepo.findByRomId(Long.parseLong(productRespone.getRomRespones().get(i).getId()));
                if(productPropertyEntities == null || productPropertyEntities.size() == 0){
                    check = false;
                    productRespone.getRomRespones().remove(i);
                    size--;
                    continue;
                }else {
                    for (ProductPropertyEntity listProductProperty : productPropertyEntities) {
                        productRespone.setPrice(convertUtil.moneyToStringFormat(listProductProperty.getPrice()));
                        productRespone.setPricePromotion(listProductProperty.getPricePromotion());
                    }
                }
            }
            if(check){
                listPro.add(productRespone);
            }
        }
        return listPro;
    }

    @Override
    public List<NewProductRespone> findByName(String name) {
        List<NewProductRespone> listPro = new ArrayList<>();
        List<ProductEntity> list = productRepo.findByName("%" + name + "%");
        for (ProductEntity product : list) {
            boolean check = true;
            NewProductRespone productRespone = new NewProductRespone();
            productRespone.setId(String.valueOf(product.getId()));
            productRespone.setName(product.getName());
            productRespone.setNote(product.getNote());
            productRespone.setImageKey(product.getImage_key());
            productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
            productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
            productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
            int size = productRespone.getRomRespones().size();
            for (int i = 0; i < size; i++) {
                List<ProductPropertyEntity> productPropertyEntities = productPropertyRepo.findByRomId(Long.parseLong(productRespone.getRomRespones().get(i).getId()));
                if(productPropertyEntities == null || productPropertyEntities.size() == 0){
                    check = false;
                    productRespone.getRomRespones().remove(i);
                    size--;
                    continue;
                }else {
                    for (ProductPropertyEntity listProductProperty : productPropertyEntities) {
                        productRespone.setPrice(convertUtil.moneyToStringFormat(listProductProperty.getPrice()));
                        productRespone.setPricePromotion(listProductProperty.getPricePromotion());
                    }
                }
            }
            if(check){
                listPro.add(productRespone);
            }
        }
        return listPro;
    }

    private ProductRespone mapToEntity(ProductEntity entity) {
        ProductRespone respone = new ProductRespone();
        respone.setId(String.valueOf(entity.getId()));
        respone.setName(entity.getName());
        respone.setNote(entity.getNote());
        respone.setImageKey(entity.getImage_key());
        return respone;
    }
}
