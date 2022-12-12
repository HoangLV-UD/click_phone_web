package com.example.word_phone_web.controller;

import com.example.word_phone_web.dto.respone.product.NewProductRespone;
import com.example.word_phone_web.dto.respone.product.ProductRespone;
import com.example.word_phone_web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;


    @GetMapping("{id}")
    public String getOneProduct(Model model, @PathVariable("id") String id) {
        List<NewProductRespone> listRandom = service.findbyRandom();

        ProductRespone respone = service.findById(Long.valueOf(id));
        if (null == respone) {
            return "redirect:/home";
        }
        model.addAttribute("product", respone);
        model.addAttribute("listRandom", listRandom);
        return "/views/product-detail";
    }
}
