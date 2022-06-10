package com.example.word_phone_web.api;

import com.example.word_phone_web.dto.respone.product.ProductRespone;
import com.example.word_phone_web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductApi {
    private  final ProductService productService;


    @GetMapping("{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") Long id){
        ProductRespone respone = productService.findById(id);
        System.out.println(respone.getRomRespones());
        return  ResponseEntity.ok().body(respone);
    }

}
