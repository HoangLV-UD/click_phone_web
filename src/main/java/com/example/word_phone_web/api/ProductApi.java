package com.example.word_phone_web.api;

import com.example.word_phone_web.dto.respone.product.NewProductRespone;
import com.example.word_phone_web.dto.respone.product.ProductRespone;
import com.example.word_phone_web.entity.ProductEntity;
import com.example.word_phone_web.repo.ProductRepo;
import com.example.word_phone_web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductApi {
    private  final ProductService productService;
    private  final ProductRepo productRepo;

    @PutMapping("/favorite/{id}")
    public ResponseEntity<?> addFavorite(@PathVariable("id") Long id){
        Optional<ProductEntity> respone = productRepo.findByIdProduct(id);
        if (respone.isPresent()){
            ProductEntity productEntity = respone.get();
            productEntity.setFavorite(true);
            productRepo.save(productEntity);
        }
        return  ResponseEntity.ok().body(respone);
    }
}
