package com.example.word_phone_web.api;

import com.example.word_phone_web.dto.respone.product.NewProductRespone;
import com.example.word_phone_web.dto.respone.product.ProductRespone;
import com.example.word_phone_web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categori")
public class CategoriApi {
    private  final ProductService productService;
//
//
//    @GetMapping("{categoriId}")
//    public List<NewProductRespone> getProductByCategori(@PathVariable("categoriId") Long id){
//        List<NewProductRespone> respone =  productService.findByCateId(id) ;
//        return  respone;
//    }
}
