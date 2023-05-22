package com.example.click_phone_web.api;

import com.example.click_phone_web.repo.ProductRepo;
import com.example.click_phone_web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductApi {
    private  final ProductService productService;
    private  final ProductRepo productRepo;

}
