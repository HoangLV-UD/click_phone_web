package com.example.word_phone_web.controller;

import com.example.word_phone_web.dto.respone.product.NewProductRespone;
import com.example.word_phone_web.entity.CategoryEntity;
import com.example.word_phone_web.service.CategoryService;
import com.example.word_phone_web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @GetMapping("")
    public String getProductByName(Model model,
                                   @RequestParam(name = "name", required = false) String name,
                                   @RequestParam(name = "categori", required = false) String id) {
        List<NewProductRespone> respone;
        if(StringUtils.hasText(name)){
            respone = productService.findByName(name);
            if (null == respone) {
                System.out.println("No found");
                return "redirect:/product/home";
            }
        }else if(StringUtils.hasText(id)){
            respone = productService.findByCateId(Long.parseLong(id));
            if (null == respone) {
                System.out.println("No found");
                return "redirect:/product/home";
            }
        }else {
            respone =  productService.findAll() ;
            model.addAttribute("products", respone);
            return "views/home/index-2";
        }
        List<CategoryEntity> categoryEntities = categoryService.findByCategoryAndDeleteFlagIsFalse();
        model.addAttribute("categories", categoryEntities);
        model.addAttribute("products", respone);
        return "views/home/index-2";
    }
}
